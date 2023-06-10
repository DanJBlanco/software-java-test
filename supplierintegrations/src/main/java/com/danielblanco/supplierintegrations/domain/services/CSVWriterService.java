package com.danielblanco.supplierintegrations.domain.services;

import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@Service
public class CSVWriterService {

    @Value("${log.file}")
    private String csvFilePath;

    private final KafkaMessageProducer kafkaMessageProducer;

    public CSVWriterService(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    public String generateActivityLogFile(String total_line) {
        String fileStatus = "NO_CREATED";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            csvPrinter.printRecord("IP", "TIMESTAMP", "ACTIVITY", "USERNAME");

            for (int i = 0; i < Integer.parseInt(total_line); i++) {
                String ip = generateRandomIP();
                long timestamp = generateRandomTimestamp();
                String signinSuccess = generateRandomSigninSuccess();
                String username = generateRandomUsername();

                csvPrinter.printRecord(ip, timestamp, signinSuccess, username);
            }

            csvPrinter.flush();
            fileStatus = "CREATED";
            kafkaMessageProducer.sendMessage(fileStatus);
            return "Archivo CSV created success.";

        } catch (IOException e) {
            e.printStackTrace();
        }

        kafkaMessageProducer.sendMessage(fileStatus);
        return "Archivo CSV NO exitosamente.";
    }

    private static String generateRandomIP() {
        Random random = new Random();
        return random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
    }

    private static long generateRandomTimestamp() {
        Random random = new Random();
        return System.currentTimeMillis() - random.nextInt(1000000000);
    }

    private static String generateRandomSigninSuccess() {
        Random random = new Random();
        return random.nextBoolean() ? "SIGNIN_SUCCESS" : "SIGNIN_FAILURE";
    }

    private static String generateRandomUsername() {
        String[] usernames = {"Will.Smith", "John.Doe", "Jane.Doe", "Alice.cooper", "Bob.marley", "Charlie.brown", "Eva.Johns", "Frank.Ana"};
        Random random = new Random();
        return usernames[random.nextInt(usernames.length)];
    }
}
