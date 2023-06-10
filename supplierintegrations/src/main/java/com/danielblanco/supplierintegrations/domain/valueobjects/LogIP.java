package com.danielblanco.supplierintegrations.domain.valueobjects;

import com.danielblanco.supplierintegrations.domain.utils.Validate;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class LogIP {

    private String value;

    public LogIP(String value) {
        this.validateIPFormat(value);
        this.value = value;
    }

    private void validateIPFormat(String value) {

        Validate.validateEmptyString(value, ExceptionMessage.IP_NULL_ERROR.getValue());

        String regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches()) {
            throw new RuntimeException(ExceptionMessage.IP_FORMAT_ERROR.getValue());
        };
    }

    public String getValue() {
        return value;
    }
}
