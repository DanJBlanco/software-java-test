package com.danielblanco.supplierintegrations.messages.kafka.consumer;

import com.danielblanco.supplierintegrations.domain.services.impl.CSVReaderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageConsumer {

    private final CSVReaderApplicationService csvReaderApplicationService;

    public KafkaMessageConsumer(CSVReaderApplicationService csvReaderApplicationService) {
        this.csvReaderApplicationService = csvReaderApplicationService;
    }



    @KafkaListener(
            topics = "${topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "${topic.group.id}")
    public void consumer(String message) {
        log.info("[KafkaMessageConsumer] consumer start...");
        log.debug("[KafkaMessageConsumer] consumer ( message: {} )", message);

        if (message.equals("CREATED")) {
            this.csvReaderApplicationService.readCSVFile();
        }
    }
}
