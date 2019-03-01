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
    FileName: StringUtils.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.tools.util;

import java.util.function.Function;

public final class StringUtils {

    /**
     * 判断字符串是否为空指针
     *
     * @param src 需要判断的字符串，可空
     * @return 仅在参数为 {@code null} 时，返回 true
     */
    public static final boolean isNull(String src) {
        return (null == src);
    }

    /**
     * 判断字符串是否不为空指针
     *
     * @param src 需要判断的字符串，可空
     * @return 只要参数不为 {@code null}，就返回 true
     */
    public static final boolean isNotNull(String src) {
        return (null != src);
    }

    /**
     * 判断字符串是否为空字符串
     *
     * @param src 需要判断的字符串，可空
     * @return 仅在参数为 {@code null}或参数为空字符串时，返回 true
     * <p>
     * 示例：
     * isEmpty(null) -> true
     * isEmpty("") -> true
     * isEmpty(" ") -> false
     * isEmpty("A") -> false
     */
    public static final boolean isEmpty(String src) {
        return (null == src || src.length() == 0);
    }

    /**
     * 判断字符串是否不为空字符串
     *
     * @param src 需要判断的字符串，可空
     * @return 只要字符串包含字符，就返回 true
     * <p>
     * 示例：
     * isNotEmpty(null) -> false
     * isNotEmpty("") -> false
     * isNotEmpty(" ") -> true
     * isNotEmpty("A") -> true
     */
    public static final boolean isNotEmpty(String src) {
        return (null != src && src.length() > 0);
    }

    /**
     * 判断字符串是否空或空格
     *
     * @param src 需要判断的字符串，可空
     * @return 字符串为空指针或空字符串或全是空格时返回 true
     * <p>
     * 示例：
     * isBlank(null) -> true
     * isBlank("") -> true
     * isBlank(" ") -> true
     * isBlank("         ") -> true
     * isBlank("  A  ") -> false
     * isBlank("A") -> false
     */
    public static final boolean isBlank(String src) {
        return (null == src || src.trim().length() == 0);
    }

    /**
     * 判断字符串是否不为空，不为空字符串且不全是空格
     *
     * @param src 需要判断的字符串，可空
     * @return 仅当字符串中包含有非空格的字符时返回 true
     * <p>
     * 示例：
     * isNotBlank(null) -> false
     * isNotBlank("") -> false
     * isNotBlank(" ") -> false
     * isNotBlank("         ") -> false
     * isNotBlank("  A  ") -> true
     * isNotBlank("A") -> true
     */
    public static final boolean isNotBlank(String src) {
        return (null != src && src.trim().length() > 0);
    }

    /**
     * 移除字符串首尾的空格
     *
     * @param src 需要移除空格的字符串，可空
     * @return 移除空格后的字符串，如果为null，则返回空字符串
     */
    public static final String trim(String src) {
        if (null == src) {
            return "";
        }
        return src.trim();
    }

    /**
     * 拼接多个小的字符串为一个大的字符串
     *
     * @param strings 需要拼接的字符串
     * @return 拼接完成的字符串
     */
    public static final String join(String... strings) {
        return joinFunction(s -> s, strings);
    }

    /**
     * 拼接多个小的字符串为一个大的字符串，以指定的分隔符连接
     *
     * @param separator 分隔符
     * @param strings   需要拼接的字符串
     * @return 拼接完成的字符串
     */
    public static final String joinWithSeparator(String separator, String... strings) {
        return joinFunction(s -> (trim(separator) + s), strings);
    }

    /**
     * 对字符串数组进行特定处理后进行拼接
     *
     * @param func    字符串数组元素处理方法
     * @param strings 需要拼接的字符串数组
     * @return 拼接完成的字符串
     */
    public static final String joinFunction(Function<String, String> func, String... strings) {
        if (null != strings && strings.length > 0) {
            if (strings.length == 1) {
                return strings[0];
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (String s : strings) {
                stringBuilder.append(func.apply(s));
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }

}
