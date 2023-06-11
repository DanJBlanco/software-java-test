package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.application.exception.ErrorTracker;
import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.mapper.LogActivityMapper;
import com.danielblanco.supplierintegrations.domain.services.HackerDetector;
import com.danielblanco.supplierintegrations.domain.services.LogActivityRepository;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HackerDetectorImpl implements HackerDetector {

    private final LogActivityMapper logActivityMapper;
    private final ErrorTracker errorTracker;
    private final LogActivityRepository logActivityRepository;


    @Value("${log.file}")
    private String csvFilePath;

    public HackerDetectorImpl(LogActivityMapper logActivityMapper,
                              ErrorTracker errorTracker, LogActivityRepository logActivityRepository) {
        this.logActivityMapper = logActivityMapper;
        this.errorTracker = errorTracker;
        this.logActivityRepository = logActivityRepository;
    }

    @Override
    public String parseLine(String line) {
        log.info("[HackerDetectorImpl] parseLine start...");
        log.debug("[HackerDetectorImpl] parseLine (line: {})", line);
        String response = null;
        try {
            LogActivity logActivity = logActivityMapper.stringToLogActivity(line);
            if ( LogAction.SIGNIN_FAILURE.equals(logActivity.getAction())) {
                boolean hasSuspiciousActivity = logActivityRepository.readCSVFile(csvFilePath, logActivity);
                response = hasSuspiciousActivity ? logActivity.getIP().getValue() : null;

            }
        } catch ( Exception e) {
            log.info(e.getMessage());
            errorTracker.setError(e);
        }

        log.info("[HackerDetectorImpl] parseLine finish...");
        return response;
    }
}
