package com.goit.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateTimeFormatters {

    private DateTimeFormatters() {
        throw new UnsupportedOperationException();
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_TIME_FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static LocalDateTime localDateTimeStringParser(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DATE_TIME_FORMATTER);
    }

    public static String localDateTimeParser(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static String localDateTimeFileFormatter(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FILE_FORMATTER);
    }
}
