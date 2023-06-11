package com.danielblanco.supplierintegrations.application.controller;

import com.danielblanco.supplierintegrations.application.exception.ErrorTracker;
import com.danielblanco.supplierintegrations.domain.services.CSVWriterService;
import com.danielblanco.supplierintegrations.domain.services.impl.CSVReaderApplicationService;
import com.danielblanco.supplierintegrations.domain.utils.Validate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
public class LogController {

    @Value("${log.file}")
    private String csvFilePath;

    private final CSVWriterService csvWriterService;
    private final CSVReaderApplicationService csvReaderApplicationService;
    private final ErrorTracker errorTracker;

    public LogController(CSVWriterService csvWriterService, CSVReaderApplicationService csvReaderApplicationService, ErrorTracker errorTracker) {
        this.csvWriterService = csvWriterService;
        this.csvReaderApplicationService = csvReaderApplicationService;
        this.errorTracker = errorTracker;
    }

    @GetMapping("/create-log")
    public ResponseEntity<?> createLog(@RequestParam("total_line") String totalLines) {

        if (Validate.validateTotalLineInteger(totalLines) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: total_lines must be an integer");
        };

        String filePath = csvWriterService.generateActivityLogFile(totalLines);
        return ResponseEntity.ok("¡Create CSV! " + filePath);
    }

    @GetMapping("/process")
    public ResponseEntity<?> processLog(){

        csvReaderApplicationService.readCSVFile();
        if( errorTracker.getErrorList().size() == 0) {
            return ResponseEntity.ok("ok");
        }
        String body = String.join("\n", errorTracker.getErrorList());

        return ResponseEntity.ok("Some errors: \n" + body);
    }

}
