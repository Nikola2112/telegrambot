package com.goit.validator;

import java.util.regex.Pattern;


public class DateValidator implements Validator {

    private static final String DATE_TIME_REGEX = "^([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})\\s([0-9]{2}):([0-9]{2})$";
    private static final Pattern DATE_TIME_PATTERN;

    static {
        DATE_TIME_PATTERN = Pattern.compile(DATE_TIME_REGEX);
    }
    @Override
    public boolean isValid(String text) {

        return DATE_TIME_PATTERN.matcher(text).matches();
    }
}
