package com.goit.validator;


import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class PhoneNumberValidator implements Validator {
    private static final String PHONE_NUMBER_REGEX = "^\\+?3?8?(0\\d{9})$";
    private static final Pattern PHONE_NUMBER_PATTERN;

    static {
        PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
    }


    @Override
    public boolean isValid(String phoneNumber) {
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}

