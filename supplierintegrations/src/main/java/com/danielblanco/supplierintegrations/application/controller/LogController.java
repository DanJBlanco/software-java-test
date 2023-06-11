package com.danielblanco.supplierintegrations.application.controller;

import com.danielblanco.supplierintegrations.application.exception.ErrorTracker;
import com.danielblanco.supplierintegrations.domain.services.CSVWriterService;
import com.danielblanco.supplierintegrations.domain.services.impl.CSVReaderApplicationService;
import com.danielblanco.supplierintegrations.domain.utils.Validate;
import com.danielblanco.supplierintegrations.messages.kafka.MessageType;
import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @Value("${log.file}")
    private String csvFilePath;

    private final CSVWriterService csvWriterService;
    private final CSVReaderApplicationService csvReaderApplicationService;
    private final ErrorTracker errorTracker;

    private final KafkaMessageProducer kafkaMessageProducer;

    public LogController(CSVWriterService csvWriterService, CSVReaderApplicationService csvReaderApplicationService, ErrorTracker errorTracker, KafkaMessageProducer kafkaMessageProducer) {
        this.csvWriterService = csvWriterService;
        this.csvReaderApplicationService = csvReaderApplicationService;
        this.errorTracker = errorTracker;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @GetMapping("/create-log")
    public ResponseEntity<?> createLog(@RequestParam("total_line") String totalLines) {

        if (Validate.validateTotalLineInteger(totalLines) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: total_lines must be an integer");
        };

        String filePath = csvWriterService.generateActivityLogFile(totalLines);
        return ResponseEntity.ok("¡Create CSV! " + filePath);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String requestLogin) {

        kafkaMessageProducer.sendMessage(MessageType.LOGIN + ":" +requestLogin);
        return ResponseEntity.ok("¡Message send! " + requestLogin);
    }


}
