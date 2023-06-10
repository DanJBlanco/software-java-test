package com.danielblanco.supplierintegrations.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class LogId {
    private UUID value;

    public LogId() {
        this.value = UUID.randomUUID();
    }

    public LogId(String value) {
        this.validateId(value);
        this.value = UUID.fromString(value);
    }

    private void validateId(String value) {
        if(Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.UUID_ERROR.getValue());
        }
    }

    public String getValue() {
        return value.toString();
    }
}
