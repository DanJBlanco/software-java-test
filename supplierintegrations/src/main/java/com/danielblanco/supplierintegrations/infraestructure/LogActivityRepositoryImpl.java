package com.danielblanco.supplierintegrations.infraestructure;

import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.mapper.LogActivityMapper;
import com.danielblanco.supplierintegrations.domain.services.LogActivityRepository;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LogActivityRepositoryImpl implements LogActivityRepository {


    private static final int BATCH_SIZE = 100;

    private final LogActivityMapper logActivityMapper;

    public LogActivityRepositoryImpl(LogActivityMapper logActivityMapper) {
        this.logActivityMapper = logActivityMapper;
    }

    @Override
    public boolean readCSVFile(String csvFilePath, LogActivity logActivityInput) {
        log.info("[CSVReaderServiceImpl] readCSVFile start...");
        log.debug("[CSVReaderServiceImpl] readCSVFile csvFilePath: {}", csvFilePath);

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader,format)) {

            Iterable<CSVRecord> records = csvParser.getRecords();

            int counter = 0;
            int failedLoginCount = 0;

            Map<String, Integer> failureCountMap = new HashMap<>();
            Duration timeWindow = Duration.ofMinutes(5);

            for (CSVRecord record : records) {

                LogActivity logActivityRecord= processCSVRecord(record);

                if( evaluateActivity(logActivityInput, logActivityRecord, timeWindow, failureCountMap)){
                    failedLoginCount++;
                }

                if (failedLoginCount >= 5) {
                    return true;
                }

                counter++;
                if (counter % BATCH_SIZE == 0) {
                    System.gc();
                }
            }

        } catch (Exception e) {
            log.error("[CSVReaderServiceImpl] readCSVFile error: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            System.gc();
        }

        log.info("[CSVReaderServiceImpl] readCSVFile finish...");
        return false;
    }

    private boolean evaluateActivity(LogActivity logActivityInput, LogActivity logActivityRecord,
                                     Duration timeWindow, Map<String, Integer> failureCountMap) {

        if (LogAction.SIGNIN_FAILURE.equals(logActivityRecord.getAction()) &&
            logActivityInput.getIP().getValue().equals(logActivityRecord.getIP().getValue())) {

            LocalDateTime logActivityRecordInputMinutes = logActivityInput.getDate().getValue().toLocalDateTime();
            LocalDateTime logActivityRecordStartMinutes = logActivityRecord.getDate().getValue().toLocalDateTime();

            long minusDif = ChronoUnit.MINUTES.between(logActivityRecordStartMinutes, logActivityRecordInputMinutes);

            return minusDif < 5;
        }

        return false;
    }

    private LogActivity processCSVRecord(CSVRecord record) throws Exception {
        log.info("[CSVReaderServiceImpl] processCSVRecord start...");
        log.info("[CSVReaderServiceImpl] processCSVRecord record: {}", record);

        StringBuilder sb = new StringBuilder();
        sb.append(record.get("IP")).append(",");
        sb.append(record.get("TIMESTAMP")).append(",");
        sb.append(record.get("ACTIVITY")).append(",");
        sb.append(record.get("USERNAME"));

        log.info("[CSVReaderServiceImpl] processCSVRecord finish...");
        return logActivityMapper.stringToLogActivity(sb.toString());
    }

}
