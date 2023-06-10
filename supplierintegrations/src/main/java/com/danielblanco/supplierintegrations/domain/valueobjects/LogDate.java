package com.danielblanco.supplierintegrations.domain.valueobjects;

import com.danielblanco.supplierintegrations.domain.utils.Validate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogDate {

    private Timestamp value;

    public LogDate(String value) {
        this.value = this.validateDate(value);;
    }

    private Timestamp validateDate(String value) {
        Validate.validateEmptyString(value, ExceptionMessage.DATE_FORMAT_ERROR.getValue());
        return new Timestamp(Long.parseLong(value));
    }

    public String getValueFormat() {

        LocalDateTime localDateTimeNoTimeZone = this.value.toLocalDateTime();
        ZonedDateTime now = localDateTimeNoTimeZone.atZone(ZoneId.systemDefault());;

        String pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
           return now.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getValue() {
        return value;
    }
}
