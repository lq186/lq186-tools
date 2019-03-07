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
    FileName: NumberUtils.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.common.util;

import java.math.BigDecimal;

public final class NumberUtils {

    /**
     * 按照指定的格式转换数值类型为字符串
     *
     * @param value      Long 类型的数值
     * @param pattern    格式
     * @param nullString 值为空时返回值
     * @return 转换后的字符串
     */
    public static final String long2String(Long value, String pattern, String nullString) {
        if (null == value) {
            return nullString;
        }
        return String.format(pattern, value);
    }

    /**
     * 转换字符串为Long类型
     *
     * @param longString 需要转换的字符串
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回null
     */
    public static final Long longFromString(String longString) {
        longString = StringUtils.trim(longString);
        if (RegExUtils.isInteger(longString)) {
            return Long.parseLong(longString);
        } else {
            return null;
        }
    }

    /**
     * 转换字符串为Long类型
     *
     * @param longString   需要转换的字符串
     * @param defaultValue 默认值，不能转换时，返回该值
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回 defaultValue
     */
    public static final long longFromString(String longString, long defaultValue) {
        longString = StringUtils.trim(longString);
        if (RegExUtils.isInteger(longString)) {
            return Long.parseLong(longString);
        } else {
            return defaultValue;
        }
    }

    /**
     * 按照指定的格式转换数值类型为字符串
     *
     * @param value      Integer 类型的数值
     * @param pattern    格式
     * @param nullString 值为空时返回值
     * @return 转换后的字符串
     */
    public static final String int2String(Integer value, String pattern, String nullString) {
        if (null == value) {
            return nullString;
        }
        return String.format(pattern, value);
    }

    /**
     * 转换字符串为Integer类型
     *
     * @param intString 需要转换的字符串
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回null
     */
    public static final Integer intFromString(String intString) {
        intString = StringUtils.trim(intString);
        if (RegExUtils.isInteger(intString)) {
            return Integer.parseInt(intString);
        } else {
            return null;
        }
    }

    /**
     * 转换字符串为Integer类型
     *
     * @param intString    需要转换的字符串
     * @param defaultValue 默认值，不能转换时，返回该值
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回 defaultValue
     */
    public static final int intFromString(String intString, int defaultValue) {
        intString = StringUtils.trim(intString);
        if (RegExUtils.isInteger(intString)) {
            return Integer.parseInt(intString);
        } else {
            return defaultValue;
        }
    }

    /**
     * 按照指定的格式转换数值类型为字符串
     *
     * @param value      BigDecimal 类型的数值
     * @param pattern    格式
     * @param nullString 值为空时返回值
     * @return 转换后的字符串
     */
    public static final String bigDecimal2String(BigDecimal value, String pattern, String nullString) {
        if (null == value) {
            return nullString;
        }
        return String.format(pattern, value);
    }

    /**
     * 按照指定的格式转换数值类型为字符串
     *
     * @param value      Float 类型的数值
     * @param pattern    格式
     * @param nullString 值为空时返回值
     * @return 转换后的字符串
     */
    public static final String float2String(Float value, String pattern, String nullString) {
        if (null == value) {
            return nullString;
        }
        return String.format(pattern, value);
    }

    /**
     * 按照指定的格式转换数值类型为字符串
     *
     * @param value      Double 类型的数值
     * @param pattern    格式
     * @param nullString 值为空时返回值
     * @return 转换后的字符串
     */
    public static final String double2String(Double value, String pattern, String nullString) {
        if (null == value) {
            return nullString;
        }
        return String.format(pattern, value);
    }

    /**
     * 转换字符串为BigDecimal类型
     *
     * @param bigDecimalString 需要转换的字符串
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回null
     */
    public static final BigDecimal bigDecimalFromString(String bigDecimalString) {
        bigDecimalString = StringUtils.trim(bigDecimalString);
        if (RegExUtils.isFloat(bigDecimalString)) {
            return new BigDecimal(bigDecimalString);
        } else {
            return null;
        }
    }

    /**
     * 转换字符串为BigDecimal类型
     *
     * @param bigDecimalString 需要转换的字符串
     * @param defaultValue     默认值，不能转换时，返回该值
     * @return 如果可以转换，返回转换后的值，如果不可转换，则返回 defaultValue
     */
    public static final BigDecimal bigDecimalFromString(String bigDecimalString, BigDecimal defaultValue) {
        bigDecimalString = StringUtils.trim(bigDecimalString);
        if (RegExUtils.isFloat(bigDecimalString)) {
            return new BigDecimal(bigDecimalString);
        } else {
            return defaultValue;
        }
    }

}
