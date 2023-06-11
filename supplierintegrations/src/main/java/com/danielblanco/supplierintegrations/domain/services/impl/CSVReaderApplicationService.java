package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.domain.services.LogActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CSVReaderApplicationService {

    private final LogActivityRepository logActivityRepository;

    @Value("${log.file}")
    private String csvFilePath;

    public CSVReaderApplicationService(LogActivityRepository logActivityRepository) {
        this.logActivityRepository = logActivityRepository;
    }
}
