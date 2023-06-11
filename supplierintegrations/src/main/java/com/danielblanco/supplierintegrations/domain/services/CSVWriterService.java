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

public interface CSVWriterService {

    public String generateActivityLogFile(String total_line);
}
