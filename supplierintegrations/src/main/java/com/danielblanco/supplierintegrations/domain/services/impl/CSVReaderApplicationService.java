package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CSVReaderApplicationService {

    private final CSVReaderService csvReaderService;

    @Value("${log.file}")
    private String csvFilePath;

    public CSVReaderApplicationService(CSVReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    public void readCSVFile() {
        log.info("[CSVReaderApplicationService] readCSVFile start...");
        csvReaderService.readCSVFile(csvFilePath);
        log.info("[CSVReaderApplicationService] readCSVFile finish");
    }
}
