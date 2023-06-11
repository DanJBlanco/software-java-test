package com.danielblanco.supplierintegrations.infraestructure;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Slf4j
@Component
public class CSVReaderServiceImpl implements CSVReaderService {


    private static final int BATCH_SIZE = 1000;

    @Override
    public void readCSVFile(String csvFilePath) {

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader,format)) {

            Iterable<CSVRecord> records = csvParser.getRecords();

            int counter = 0;
            for (CSVRecord record : records) {
                processCSVRecord(record);

                counter++;

                if (counter % BATCH_SIZE == 0) {
                    System.gc();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processCSVRecord(CSVRecord record) {
        String ip = record.get("IP");
        long timestamp = Long.parseLong(record.get("TIMESTAMP"));
        String signinSuccess = record.get("ACTIVITY");
        String username = record.get("USERNAME");

        log.info("ip: {}, time: {}, activity: {}, username: {}", ip, timestamp, signinSuccess, username);

    }

}
