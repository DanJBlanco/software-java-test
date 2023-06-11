package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.application.exception.ErrorTracker;
import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.mapper.LogActivityMapper;
import com.danielblanco.supplierintegrations.domain.services.LogActivityRepository;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogDate;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogIP;
import com.danielblanco.supplierintegrations.domain.valueobjects.UserName;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class HackerDetectorImplTest {

    private HackerDetectorImpl hackerDetector;

    @Mock
    private LogActivityMapper logActivityMapper;

    @Mock
    private ErrorTracker errorTracker;


    @Mock
    private LogActivityRepository logActivityRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hackerDetector = new HackerDetectorImpl(logActivityMapper, errorTracker, logActivityRepository);
    }

    @Test
    public void parseLineSignInFailureLogErrorTest() throws Exception {

        String csvFilePath = new ClassPathResource("test.csv").getFile().getPath() ;

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader,format)) {

            Iterable<CSVRecord> records = csvParser.getRecords();

            records.forEach( rec -> {

                LogActivity logActivity = LogActivity.builder()
                        .IP(new LogIP(rec.get(0)))
                        .date(new LogDate(rec.get(1)))
                        .action(LogAction.valueOf(rec.get(2)))
                        .userName(new UserName(rec.get(3)))
                        .build();

                try {
                    when(logActivityMapper.stringToLogActivity(rec.toString())).thenReturn(logActivity);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                hackerDetector.parseLine(rec.toString());
            });

        }

        String line = "177.10.26.217,1686225329737,SIGNIN_FAILURE,John.Doe";
        LogActivity logActivity = new LogActivity();
        logActivity.setAction(LogAction.SIGNIN_FAILURE);
        logActivity.setDate(new LogDate("1686225329737"));
        logActivity.setIP(new LogIP("177.10.26.217"));

        when(logActivityMapper.stringToLogActivity(line)).thenReturn(logActivity);

        String result = hackerDetector.parseLine(line);

        verify(logActivityMapper).stringToLogActivity(line);
        //verify(errorTracker, never()).setError(any());

        assertEquals(null, result);
    }

}