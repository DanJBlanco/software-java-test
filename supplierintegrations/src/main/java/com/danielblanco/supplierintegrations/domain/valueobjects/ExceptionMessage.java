package com.danielblanco.supplierintegrations.domain.valueobjects;

public enum ExceptionMessage {
    UUID_ERROR("Error: ID need to be a UUID"),
    IP_NULL_ERROR("Error: IP null"),
    IP_FORMAT_ERROR("Error: IP have to be with format e.g: 192.168.0.1"),
    DATE_FORMAT_ERROR("Error: Date should have timestamp format"),
    ACTION_ERROR("Error: ACTION_ERROR"),
    USERNAME_ERROR("Error: Username has null value"),
    USERNAME_FORMAT_ERROR("Error: Username format should have a '.'");
    private final String value;

    ExceptionMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
