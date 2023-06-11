package com.danielblanco.supplierintegrations.application.controller;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import com.danielblanco.supplierintegrations.domain.services.CSVWriterService;
import com.danielblanco.supplierintegrations.domain.services.impl.CSVReaderApplicationService;
import com.danielblanco.supplierintegrations.domain.utils.Validate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
public class LogController {

    @Value("${log.file}")
    private String csvFilePath;

    private final CSVWriterService csvWriterService;
    private final CSVReaderApplicationService csvReaderApplicationService;

    public LogController(CSVWriterService csvWriterService, CSVReaderApplicationService csvReaderApplicationService) {
        this.csvWriterService = csvWriterService;
        this.csvReaderApplicationService = csvReaderApplicationService;
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
        return ResponseEntity.ok("ok");
    }

}
