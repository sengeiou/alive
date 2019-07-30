package com.alive.ruo.microservice.commons.core.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.utils
 * @title: DateUtil
 * @description: TODO
 * @date 2019/4/24 18:03
 */
public class DateUtil {

    private static volatile Map<String, DateTimeFormatter> dateFormatMap = new ConcurrentHashMap<String, DateTimeFormatter>();

    public static String formatDate(Date date, String pattern) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return localDateTime.format(dateTimeFormatter);
    }

    public static Date parseDate(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static DateTimeFormatter getDateTimeFormatter(String pattern) {
        DateTimeFormatter dateTimeFormatter = dateFormatMap.get(pattern);
        if (dateTimeFormatter == null) {
            DateTimeFormatter newDateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            dateTimeFormatter = dateFormatMap.putIfAbsent(pattern, newDateTimeFormatter);
            if (dateTimeFormatter == null) {
                dateTimeFormatter = newDateTimeFormatter;
            }
        }

        return dateTimeFormatter;
    }
}
