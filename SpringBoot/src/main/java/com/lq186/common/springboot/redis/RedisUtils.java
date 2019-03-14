/*    
    Copyright ©2019 lq186.com 
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
/*
    FileName: RedisUtils.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.common.springboot.redis;

import com.lq186.common.log.Log;
import com.lq186.common.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.*;
import java.util.concurrent.TimeUnit;

public final class RedisUtils {

    private static final Log log = Log.getLog(RedisUtils.class);

    private static RedisTemplate<String, Object> redisTemplate;

    private static final TimeUnit SECONDS = TimeUnit.SECONDS;

    public static final boolean isEnabled() {
        return null != redisTemplate;
    }

    public static final void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static final boolean setExpireSeconds(String redisKey, long expireSeconds) {
        if (expireSeconds > 0 && StringUtils.isNotBlank(redisKey)) {
            log.debugf("Set expire [%d] seconds for key [%s]", expireSeconds, redisKey);
            return redisTemplate.expire(redisKey, expireSeconds, SECONDS);
        } else {
            return false;
        }
    }

    public static final long getExpireSeconds(String redisKey) {
        long expireSeconds = StringUtils.isBlank(redisKey) ? 0 : redisTemplate.getExpire(redisKey, SECONDS);
        log.debugf("Get expire [%d] seconds for key [%s]", expireSeconds, redisKey);
        return expireSeconds;
    }


    public static final boolean hasKey(String redisKey) {
        boolean hasKey = StringUtils.isBlank(redisKey) ? false : redisTemplate.hasKey(redisKey);
        log.debugf("Has key [%s] stored? %b", redisKey, hasKey);
        return hasKey;
    }

    public static final void deleteByKey(String... redisKeys) {
        if (null != redisKeys && redisKeys.length > 0) {
            log.debugf("Request to delete keys, length -> %d", redisKeys.length);
            long deleteCount = redisTemplate.delete(Arrays.asList(redisKeys));
            log.debugf("Delete %d keys", deleteCount);
        }
    }


    public static final Object getValue(String redisKey) {
        Object value = StringUtils.isBlank(redisKey) ? null : redisTemplate.opsForValue().get(redisKey);
        log.debugf("Get key [%s] from redis, value -> %s", redisKey, String.valueOf(value));
        return value;
    }

    public static final boolean setValue(String redisKey, Object value) {
        if (StringUtils.isBlank(redisKey)) {
            log.debugf("Redis key [%s] is blank", redisKey);
            return false;
        } else {
            if (null == value) {
                log.debugf("Store value is null, use RedisObject instead");
                value = RedisObjectHolder.nullObject();
            }
            log.debugf("Store value [%s] use key [%s]", String.valueOf(value), redisKey);
            redisTemplate.opsForValue().set(redisKey, value);
            return true;
        }
    }

    public static final boolean delete(String redisKey) {
        log.debugf("Call delete key [%s] from redis.", redisKey);
        return redisTemplate.delete(redisKey);
    }

    public static final long delete(Collection<String> redisKey) {
        log.debugf("Call delete key [%s] from redis.", redisKey);
        Long count = redisTemplate.delete(redisKey);
        return null == count ? 0 : count;
    }

    public static final boolean setValueWithExpireSeconds(String redisKey, Object value, long expireSeconds) {
        if (StringUtils.isBlank(redisKey)) {
            log.debugf("Redis key [%s] is blank", redisKey);
            return false;
        } else {
            if (null == value) {
                log.debugf("Store value is null, use RedisObject instead");
                value = RedisObjectHolder.nullObject();
            }
            log.debugf("Store value [%s] use key [%s], with expire [%d] seconds", String.valueOf(value), redisKey, expireSeconds);
            redisTemplate.opsForValue().set(redisKey, value, expireSeconds, SECONDS);
            return true;
        }
    }


    public static final long incrementValue(String redisKey, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (StringUtils.isBlank(redisKey)) {
            throw new RuntimeException("递减键值不能为空");
        }
        return redisTemplate.opsForValue().increment(redisKey, delta);
    }

    public static final long decrementValue(String redisKey, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (StringUtils.isBlank(redisKey)) {
            throw new RuntimeException("递减键值不能为空");
        }
        return redisTemplate.opsForValue().decrement(redisKey, delta);
    }


    public static final Object getHashItem(String redisKey, String hashItemKey) {
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(hashItemKey)) {
            return RedisObjectHolder.nullObject();
        }
        Object hashValue = redisTemplate.opsForHash().get(redisKey, hashItemKey);
        log.debugf("Get hash item [%s] use key [%s], got value -> %s", hashItemKey, redisKey, String.valueOf(hashValue));
        return hashValue;
    }

    public static final Map<Object, Object> getHashItems(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return null.");
            return null;
        }
        Map<Object, Object> hashItems = redisTemplate.opsForHash().entries(redisKey);
        if (null == hashItems) {
            log.debugf("No hash value found for key [%s] found", redisKey);
        } else {
            log.debugf("Found hash value (size: %d) for key [%s]", hashItems.size(), redisKey);
        }
        return hashItems;
    }

    public static final boolean putHashItems(String redisKey, Map<String, Object> hashMap) {
        if (StringUtils.isBlank(redisKey) || null == hashMap || hashMap.size() == 0) {
            log.debugf("Redis key is blank or hashMap is empty");
            return false;
        }
        log.debugf("Put [%d] hash items for redis key [%s]", hashMap.size(), redisKey);
        redisTemplate.opsForHash().putAll(redisKey, hashMap);
        return true;
    }

    public static final boolean putHashItemsWithExpireSeconds(String redisKey, Map<String, Object> hashMap, long expireSeconds) {
        boolean isPutSuccess = putHashItems(redisKey, hashMap);
        if (isPutSuccess) {
            return setExpireSeconds(redisKey, expireSeconds);
        }
        return false;
    }

    public static final boolean putHashItem(String redisKey, String hashItemKey, Object hashItemValue) {
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(hashItemKey)) {
            log.debugf("Redis key is blank or hash item key is blank");
            return false;
        }
        if (null == hashItemValue) {
            log.debug("Hash item value is null, use RedisObject instead");
            hashItemValue = RedisObjectHolder.nullObject();
        }
        log.debugf("Put hash item [%s, %s] to redis key [%s]", hashItemKey, String.valueOf(hashItemValue), redisKey);
        redisTemplate.opsForHash().put(redisKey, hashItemKey, hashItemValue);
        return true;
    }

    public static final void deleteHashItem(String redisKey, String... hashItemKeys) {
        if (StringUtils.isBlank(redisKey) || null == hashItemKeys || hashItemKeys.length == 0) {
            log.debug("Redis key is blank or hash item keys is empty");
            return;
        }
        log.debugf("Delete [%d] hash item for redis key %s", hashItemKeys.length, redisKey);
        redisTemplate.opsForHash().delete(redisKey, hashItemKeys);
    }

    public static final long incrementHashItemValue(String redisKey, String hashItemKey, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(hashItemKey)) {
            throw new RuntimeException("递增键值不能为空");
        }
        return redisTemplate.opsForHash().increment(redisKey, hashItemKey, delta);
    }

    public static final long decrementHashItemValue(String redisKey, String hashItemKey, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(hashItemKey)) {
            throw new RuntimeException("递减键值不能为空");
        }
        return redisTemplate.opsForHash().increment(redisKey, hashItemKey, -delta);
    }


    public static final Set<Object> getSet(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return null");
            return null;
        }
        Set<Object> set = redisTemplate.opsForSet().members(redisKey);
        log.debugf("Got [%d] setUserInfo items for redis key [%s]", null == set ? 0 : set.size(), redisKey);
        return set;
    }

    public static final boolean hasValueInSet(String redisKey, Object value) {
        if (StringUtils.isBlank(redisKey) || null == value) {
            log.debug("Redis key is blank or value is null, return false");
            return false;
        }
        return redisTemplate.opsForSet().isMember(redisKey, value);
    }

    public static final long add2Set(String redisKey, Object... values) {
        if (StringUtils.isBlank(redisKey) || null == values || values.length == 0) {
            log.debug("Redis key is blank or values is empty, return 0");
            return 0;
        }
        return redisTemplate.opsForSet().add(redisKey, values);
    }

    public static final long setSize(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return 0");
            return 0;
        }
        return redisTemplate.opsForSet().size(redisKey);
    }

    public static final long removeFromSet(String redisKey, Object... values) {
        if (StringUtils.isBlank(redisKey) || null == values || values.length == 0) {
            log.debug("Redis key is blank or values is empty, return 0");
            return 0;
        }
        return redisTemplate.opsForSet().remove(redisKey, values);
    }


    public static final List<Object> getList(String redisKey, long begin, long end) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return null");
            return null;
        }
        return redisTemplate.opsForList().range(redisKey, begin, end);
    }

    public static final long listSize(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return 0");
            return 0;
        }
        return redisTemplate.opsForList().size(redisKey);
    }

    public static final Object getValueFromList(String redisKey, long itemIndex) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return null");
            return null;
        }
        return redisTemplate.opsForList().index(redisKey, itemIndex);
    }

    public static final boolean add2List(String redisKey, Object value) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return false");
            return false;
        }
        redisTemplate.opsForList().rightPush(redisKey, value);
        return true;
    }

    public static final long addAll2List(String redisKey, List<Object> valueList) {
        if (StringUtils.isBlank(redisKey) || null == valueList || valueList.size() == 0) {
            log.debug("Redis key is blank or valueList is empty, return 0");
            return 0;
        }
        return redisTemplate.opsForList().rightPushAll(redisKey, valueList);
    }

    public static final boolean updateListValueByIndex(String redisKey, long itemIndex, Object value) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return false");
            return false;
        }
        redisTemplate.opsForList().set(redisKey, itemIndex, value);
        return true;
    }

    public static final long removeFromList(String redisKey, long count, Object value) {
        if (StringUtils.isBlank(redisKey)) {
            log.debug("Redis key is blank, return 0");
            return 0;
        }
        return redisTemplate.opsForList().remove(redisKey, count, value);
    }


    public static final boolean lock(String redisKey, String lockId, long expireSeconds) {
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(lockId)) {
            log.debug("Redis key is blank or lockId is blank, return false");
            return false;
        }
        RedisScript<Long> redisScript = new DefaultRedisScript<>(LOCK_LUA, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(redisKey), lockId, expireSeconds);
        return null != result && result > 0;
    }

    public static final boolean unlock(String redisKey, String lockId) {
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(lockId)) {
            log.debug("Redis key is blank or lockId is blank, return false");
            return false;
        }
        RedisScript<Long> redisScript = new DefaultRedisScript<>(UNLOCK_LUA, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(redisKey), lockId);
        return null != result && result > 0;
    }

    public static final <T> T execute(Class<T> classOfT, String script, List<String> redisKeys, Object... args) {
        if (StringUtils.isBlank(script) || null == redisKeys || redisKeys.size() == 0) {
            log.debug("Script is blank or redis keys is empty, return null");
            return null;
        }
        RedisScript<T> redisScript = new DefaultRedisScript<>(script, classOfT);
        return redisTemplate.execute(redisScript, redisKeys, args);
    }

    private static final String UNLOCK_LUA = "" +
            "if redis.call('GET', KEYS[1]) == ARGV[1] " +
            "then " +
            "   return redis.call('DEL', KEYS[1]) " +
            "else " +
            "   return -1 " +
            "end";

    private static final String LOCK_LUA = "" +
            "if redis.call('SETNX', KEYS[1], ARGV[1]) " +
            "then " +
            "   if redis.call('GET', KEYS[1]) == ARGV[1] " +
            "   then " +
            "       return redis.call('EXPIRE', KEYS[1], ARGV[2]) " +
            "   else " +
            "       return -1 " +
            "   end " +
            "else " +
            "   return -2 " +
            "end";

}
