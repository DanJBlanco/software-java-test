package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.application.exception.ErrorTracker;
import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.mapper.LogActivityMapper;
import com.danielblanco.supplierintegrations.domain.services.HackerDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HackerDetectorImpl implements HackerDetector {

    private final LogActivityMapper logActivityMapper;
    private final ErrorTracker errorTracker;

    public HackerDetectorImpl(LogActivityMapper logActivityMapper, ErrorTracker errorTracker) {
        this.logActivityMapper = logActivityMapper;
        this.errorTracker = errorTracker;
    }

    @Override
    public String parseLine(String line) {
        log.info("[HackerDetectorImpl] parseLine start...");
        log.debug("[HackerDetectorImpl] parseLine (line: {})", line);

        try {
            LogActivity logActivity = logActivityMapper.stringToLogActivity(line);
        } catch ( Exception e) {
            log.info(e.getMessage());
            errorTracker.setError(e);
        }

        log.info("[HackerDetectorImpl] parseLine finish...");
        return null;
    }
}
