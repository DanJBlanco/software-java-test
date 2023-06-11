package com.danielblanco.supplierintegrations.application.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
@Data
@Slf4j
public class ErrorTracker {

    private List<String> errorList;

    public ErrorTracker(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setError(Exception e) {
        log.info("___________ ERROR");
        errorList.add(e.getMessage());
    }

}
