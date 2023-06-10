package com.danielblanco.supplierintegrations.messages.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageConsumer {

    @KafkaListener(
            topics = "${topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "${topic.group.id}")
    public void consumer(String message) {
        System.out.println("-----------");
        log.info("Consuming Message {}", message);
    }
}
