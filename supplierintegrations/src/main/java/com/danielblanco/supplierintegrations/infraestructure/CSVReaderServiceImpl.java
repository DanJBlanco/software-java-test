package com.danielblanco.supplierintegrations.infraestructure;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import com.danielblanco.supplierintegrations.domain.services.HackerDetector;
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

    private final HackerDetector hackerDetector;

    public CSVReaderServiceImpl(HackerDetector hackerDetector) {
        this.hackerDetector = hackerDetector;
    }

    @Override
    public void readCSVFile(String csvFilePath) {
        log.info("[CSVReaderServiceImpl] readCSVFile start...");
        log.debug("[CSVReaderServiceImpl] readCSVFile csvFilePath: {}", csvFilePath);

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
            log.error("[CSVReaderServiceImpl] readCSVFile error: {}", e.getMessage());
            e.printStackTrace();
        }
        log.info("[CSVReaderServiceImpl] readCSVFile finish...");
    }

    private void processCSVRecord(CSVRecord record) {
        log.info("[CSVReaderServiceImpl] processCSVRecord start...");
        log.debug("[CSVReaderServiceImpl] processCSVRecord record: {}", record);

        StringBuilder sb = new StringBuilder();
        sb.append(record.get("IP")).append(",");
        sb.append(record.get("TIMESTAMP")).append(",");
        sb.append(record.get("ACTIVITY")).append(",");
        sb.append(record.get("USERNAME"));

        this.hackerDetector.parseLine(sb.toString());
        log.info("[CSVReaderServiceImpl] processCSVRecord finish...");

    }

}
