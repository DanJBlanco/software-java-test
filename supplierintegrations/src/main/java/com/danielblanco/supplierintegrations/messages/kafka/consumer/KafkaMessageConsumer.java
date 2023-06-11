package com.danielblanco.supplierintegrations.messages.kafka.consumer;

import com.danielblanco.supplierintegrations.domain.services.CSVReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageConsumer {

    private final CSVReaderService csvReaderService;

    public KafkaMessageConsumer(CSVReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @Value("${log.file}")
    private String csvFilePath;


    @KafkaListener(
            topics = "${topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "${topic.group.id}")
    public void consumer(String message) {
        log.info("Consuming Message {}", message);

        if (message.equals("CREATED")) {
            this.csvReaderService.readCSVFile(csvFilePath);
        }
    }
}
