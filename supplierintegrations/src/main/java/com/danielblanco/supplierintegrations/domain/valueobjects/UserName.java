package com.danielblanco.supplierintegrations.domain.valueobjects;

import com.danielblanco.supplierintegrations.domain.utils.Validate;

public class UserName {
    private String value;

    public UserName(String value) {
        validateUserName(value);
        this.value = value;
    }

    private void validateUserName( String value) {
        Validate.validateEmptyString(value, ExceptionMessage.USERNAME_ERROR.getValue());
        if( !value.contains(".")) {
            throw new IllegalArgumentException(ExceptionMessage.USERNAME_FORMAT_ERROR.getValue());
        }

    }

    public String getValue() {
        return value;
    }
}
