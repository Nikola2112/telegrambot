package com.goit.validator;

import org.springframework.stereotype.Component;

@Component
public interface Validator {
    boolean isValid(String value);
}