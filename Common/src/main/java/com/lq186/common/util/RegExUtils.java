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
    FileName: RegExUtils.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.common.util;

import java.util.regex.Pattern;

public final class RegExUtils {

    /**
     * 判定字符串是否是整数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isInteger(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return isMatchesNumber("^-?\\d+$", value);
    }

    /**
     * 判定字符串是否是无符号数字
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isUnsignedInteger(String value) {
        return isMatchesNumber("^\\d+$", value);
    }

    /**
     * 判定字符串是否是正整数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isPositiveInteger(String value) {
        return isMatchesNumber("^[0-9]*[1-9][0-9]*$", value);
    }

    /**
     * 判定字符串是否是负整数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isNegativeInteger(String value) {
        return isMatchesNumber("^-[0-9]*[1-9][0-9]*$", value);
    }

    /**
     * 判定字符串是否是浮点数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isFloat(String value) {
        return isMatchesNumber("^(-?\\d+)(\\.\\d+)?$", value);
    }

    /**
     * 判定字符串是否是正浮点数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isPositiveFloat(String value) {
        return isMatchesNumber("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$", value);
    }

    /**
     * 判定字符串是否是负浮点数
     *
     * @param value 需要判定的字符串
     * @return
     */
    public static final boolean isNegativeFloat(String value) {
        return isMatchesNumber("^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$", value);
    }

    static final boolean isMatchesNumber(String regex, String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return Pattern.matches(regex, value);
    }
}
