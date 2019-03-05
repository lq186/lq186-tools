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
    FileName: RandomUtils.java
    Date: 2019/2/28
    Author: lq
*/
package com.lq186.tools.util;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public final class RandomUtils {

    static final char[] RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 获取一个随机的UUID字符串，该字符串中不包含字符'-'
     *
     * @return UUID字符串，非空
     */
    public static final String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获取一个介于两数值之间的随机数
     *
     * @param begin 开始数值，非空，必须小于end
     * @param end   结束数值，非空，必须大于begin
     * @return 随机数字
     */
    public static final long randomLong(long begin, long end) {
        if (end <= begin) {
            throw new RuntimeException("end必须大于begin");
        }
        if ((int) (end - begin) < 0) {
            throw new RuntimeException("随机区间太大，请适当增加begin值或减小end值");
        }
        return RandomHolder.RANDOM.nextInt((int) (end - begin)) + begin;
    }

    /**
     * 获取一个介于两数值之间的随机数
     *
     * @param begin 开始数值，非空，必须小于end
     * @param end   结束数值，非空，必须大于begin
     * @return 随机数字
     */
    public static final int randomInt(int begin, int end) {
        if (end <= begin) {
            throw new RuntimeException("end必须大于begin");
        }
        return RandomHolder.RANDOM.nextInt(end - begin) + begin;
    }

    /**
     * 获取一个指定长度的随机的字符串
     *
     * @param length 字符串的长度，非空，必须大于0
     * @return 随机的字符串
     */
    public static final String randomString(int length) {
        return randomSupplier(length, () -> RANDOM_CHARS[randomInt(0, RANDOM_CHARS.length)]);
    }

    /**
     * 获取一个指定长度的随机数字串
     * @param length 数字串的长度，非空，必须大于0
     * @return 随机的数字串
     */
    public static final String randomNumberic(int length) {
        return randomSupplier(length, () -> randomInt(0, 10));
    }

    /**
     * 使用生产函数方法生成一个指定长度的随机字符串
     * @param length 字符串的长度，非空，必须大于0
     * @param supplier 字符串元素提供函数方法
     * @param <T> 任意类型
     * @return 随机产生的字符串
     */
    public static final <T> String randomSupplier(int length, Supplier<T> supplier) {
        if (length <= 0) {
            throw new RuntimeException("length必须大于0");
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(supplier.get());
        }
        return builder.toString();
    }

    static class RandomHolder {
        static final Random RANDOM = new Random();
    }
}
