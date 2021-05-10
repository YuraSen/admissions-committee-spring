package com.senin.demo.util;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationErrorUtils {



    public static Map<String, String> getErrorsMap(Errors errors) {
        return errors.getFieldErrors()
                .stream()
                .collect(Collectors
                        .toMap(fieldError -> fieldError.getField() + "Error",
                                FieldError::getDefaultMessage,
                                (s, s2) -> s + ", " + s2));

    }
}
