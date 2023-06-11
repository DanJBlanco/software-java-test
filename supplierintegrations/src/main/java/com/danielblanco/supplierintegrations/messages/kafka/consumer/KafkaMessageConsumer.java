package com.danielblanco.supplierintegrations.messages.kafka.consumer;

import com.danielblanco.supplierintegrations.domain.services.impl.HackerDetectorImpl;
import com.danielblanco.supplierintegrations.messages.kafka.MessageType;
import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class KafkaMessageConsumer {

    private final HackerDetectorImpl hackerDetector;
    private final KafkaMessageProducer kafkaMessageProducer;

    public KafkaMessageConsumer(HackerDetectorImpl hackerDetector, KafkaMessageProducer kafkaMessageProducer) {
        this.hackerDetector = hackerDetector;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }



    @KafkaListener(
            topics = "${topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "${topic.group.id}")
    public void consumer(String message) {
        log.info("[KafkaMessageConsumer] consumer start...");
        log.debug("[KafkaMessageConsumer] consumer ( message: {} )", message);

        if(message.contains(MessageType.LOGIN.toString())){

            String messageValue = message.substring(message.indexOf(":")+1);
            String ban = hackerDetector.parseLine(messageValue);

            if (Objects.nonNull(ban)) {
                kafkaMessageProducer.sendMessage(MessageType.BAN_ACCOUNT+":"+ban);
            };

        }
    }
}
