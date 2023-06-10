package com.danielblanco.supplierintegrations.domain.utils;

import java.util.Objects;

public class Validate {

    public static void validateEmptyString(String value, String errorMessage) {
        if(Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
