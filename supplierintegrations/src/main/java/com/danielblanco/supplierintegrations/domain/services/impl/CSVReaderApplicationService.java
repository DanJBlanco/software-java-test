package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CSVReaderApplicationService {

    private final CSVReaderService csvReaderService;

    @Value("${log.file}")
    private String csvFilePath;

    public CSVReaderApplicationService(CSVReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }
    public void readCSVFile() {
        csvReaderService.readCSVFile(csvFilePath);
    }
}
