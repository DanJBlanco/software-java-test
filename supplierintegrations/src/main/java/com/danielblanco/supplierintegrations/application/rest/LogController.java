package com.danielblanco.supplierintegrations.application.rest;

import com.danielblanco.supplierintegrations.domain.services.CSVWriterService;
import com.danielblanco.supplierintegrations.domain.utils.Validate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LogController {

    private final CSVWriterService csvWriterService;

    public LogController(CSVWriterService csvWriterService) {
        this.csvWriterService = csvWriterService;
    }

    @GetMapping("/create-log")
    public ResponseEntity<?> createLog(@RequestParam("total_line") String totalLines) {

        if (Validate.validateTotalLineInteger(totalLines) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: total_lines must be an integer");
        };

        String filePath = csvWriterService.generateActivityLogFile(totalLines);
        return ResponseEntity.ok("¡Create CSV! " + filePath);
    }

}
