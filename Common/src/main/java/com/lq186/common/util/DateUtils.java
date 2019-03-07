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
    FileName: DateUtils.java
    Date: 2019/2/21
    Author: lq
*/
package com.lq186.common.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public final class DateUtils {

    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

    /**
     * 获取当前月份
     *
     * @return
     */
    public static final int getMonth() {
        return getMonth(new Date());
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static final int getMonth(Date date) {
        return localDateTimeFromDate(date).getMonth().getValue();
    }

    /**
     * 从 {@code Date} 实例转换为 {@code LocalDateTime} 的实例
     *
     * @param date 用来转换的 {@code Date} 实例，非空
     * @return 转换的 {@code LocalDateTime} 实例，非空
     */
    public static final LocalDateTime localDateTimeFromDate(Date date) {
        Objects.requireNonNull(date, "date");
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 从 {@code Date} 实例转换为 {@code LocalDate} 的实例
     *
     * @param date 用来转换的 {@code Date} 实例，非空
     * @return 转换的 {@code LocalDate} 实例，非空
     */
    public static final LocalDate localDateFromDate(Date date) {
        Objects.requireNonNull(date, "date");
        return localDateTimeFromDate(date).toLocalDate();
    }

    /**
     * 从 {@code LocalDateTime} 实例转换为 {@code Date} 的实例
     *
     * @param localDateTime 用来转换的 {@code LocalDateTime} 实例，非空
     * @return 转换后的 {@code Date} 的实例，非空
     */
    public static final Date dateFromLocalDateTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 从 {@code LocalDate} 实例转换为 {@code Date} 的实例
     *
     * @param localDate 用来转换的 {@code LocalDate} 实例，非空
     * @return 转换后的 {@code Date} 的实例，非空
     */
    public static final Date dateFromLocalDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "localDate");
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 从一个特殊格式的字符串转换为对应的 {@code LocalDate} 实例
     *
     * @param localDateString 用来转换的字符串，非空
     * @param pattern         字符串的格式，非空 {@see DatePattern}
     * @return 转换后的 {@code LocalDate} 实例，非空
     */
    public static final LocalDate localDateFromString(String localDateString, String pattern) {
        Objects.requireNonNull(localDateString, "localDateString");
        Objects.requireNonNull(pattern, "pattern");
        return LocalDate.parse(localDateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 从一个特殊格式的字符串转换为对应的 {@code LocalDateTime} 实例
     *
     * @param localDateTimeString 用来转换的字符串，非空
     * @param pattern             字符串的格式，非空 {@see DatePattern}
     * @return 转换后的 {@code LocalDateTime} 实例，非空
     */
    public static final LocalDateTime localDateTimeFromString(String localDateTimeString, String pattern) {
        Objects.requireNonNull(localDateTimeString, "localDateTimeString");
        Objects.requireNonNull(pattern, "pattern");
        return LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 从一个特殊格式的字符串转换为对应的 {@code Date} 实例
     *
     * @param dateString 用来转换的字符串，非空
     * @param pattern    字符串的格式，非空 {@see DatePattern}
     * @return 转换后的 {@code Date} 实例，非空
     */
    public static final Date dateFromString(String dateString, String pattern) {
        Objects.requireNonNull(dateString, "dateString");
        Objects.requireNonNull(pattern, "pattern");
        try {
            return new SimpleDateFormat(pattern).parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从一个特殊格式的字符串转换为对应的 {@code LocalTime} 实例
     *
     * @param localTimeString 用来转换的字符串，非空
     * @param pattern         字符串的格式，非空 {@see DatePattern}
     * @return 转换后的 {@code LocalTime} 实例，非空
     */
    public static final LocalTime localTimeFromString(String localTimeString, String pattern) {
        Objects.requireNonNull(localTimeString, "localTimeString");
        Objects.requireNonNull(pattern, "pattern");
        return LocalTime.parse(localTimeString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 把 {@code LocalDateTime} 实例转换为指定格式的字符串
     *
     * @param localDateTime 需要转换的 {@code LocalDateTime} 实例，非空
     * @param pattern       字符串的格式，非空 {@see DatePattern}
     * @return 转换后的特殊格式字符串
     */
    public static final String localDateTime2String(LocalDateTime localDateTime, String pattern) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(pattern, "pattern");
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    /**
     * 把 {@code Date} 实例转换为指定格式的字符串
     *
     * @param date    需要转换的 {@code Date} 实例，非空
     * @param pattern 字符串的格式，非空 {@see DatePattern}
     * @return 转换后的特殊格式字符串
     */
    public static final String date2String(Date date, String pattern) {
        Objects.requireNonNull(date, "date");
        Objects.requireNonNull(pattern, "pattern");
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把 {@code LocalDate} 实例转换为指定格式的字符串
     *
     * @param localDate 需要转换的 {@code LocalDate} 实例，非空
     * @param pattern   字符串的格式，非空 {@see DatePattern}
     * @return 转换后的特殊格式字符串
     */
    public static final String localDate2String(LocalDate localDate, String pattern) {
        Objects.requireNonNull(localDate, "localDate");
        Objects.requireNonNull(pattern, "pattern");
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * 把 {@code LocalTime} 实例转换为指定格式的字符串
     *
     * @param localTime 需要转换的 {@code LocalTime} 实例，非空
     * @param pattern   字符串的格式，非空 {@see DatePattern}
     * @return 转换后的特殊格式字符串
     */
    public static final String localTime2String(LocalTime localTime, String pattern) {
        Objects.requireNonNull(localTime, "localTime");
        Objects.requireNonNull(pattern, "pattern");
        return DateTimeFormatter.ofPattern(pattern).format(localTime);
    }

    /**
     * 从一个毫秒数字转换为一个 {@code LocalDateTime} 实例
     *
     * @param milliSeconds 毫秒数，非空
     * @return 转换后的 {@code LocalDateTime} 实例，非空
     */
    public static final LocalDateTime localDateFromMilliSeconds(Long milliSeconds) {
        Objects.requireNonNull(milliSeconds, "milliSeconds");
        return localDateFromSeconds(milliSeconds / 1000);
    }

    /**
     * 从秒数转换为一个 {@code LocalDateTime} 实例
     *
     * @param seconds 秒数，非空
     * @return 转换后的 {@code LocalDateTime} 实例，非空
     */
    public static final LocalDateTime localDateFromSeconds(Long seconds) {
        Objects.requireNonNull(seconds, "seconds");
        return LocalDateTime.ofEpochSecond(seconds, 0, DEFAULT_ZONE_OFFSET);
    }

    /**
     * 从一个{@code LocalDateTime} 实例获取到秒数
     *
     * @param localDateTime 获取秒数的实例
     * @return 秒数
     */
    public static final long localDateTime2Seconds(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.toEpochSecond(DEFAULT_ZONE_OFFSET);
    }

    /**
     * 从一个{@code LocalDateTime} 实例获取到毫秒数
     *
     * @param localDateTime 获取毫秒数的实例
     * @return 毫秒数
     */
    public static final long localDateTime2MilliSeconds(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli();
    }
}
