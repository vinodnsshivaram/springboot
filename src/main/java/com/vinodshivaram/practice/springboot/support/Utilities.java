package com.vinodshivaram.practice.springboot.support;

import org.apache.commons.lang.RandomStringUtils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {

    public static String getCurrentDateTimeFormatted() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;
        return dateTimeFormatter.format(ZonedDateTime.now());
    }

    public static String generateAlphaNumericStringOfLength(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
