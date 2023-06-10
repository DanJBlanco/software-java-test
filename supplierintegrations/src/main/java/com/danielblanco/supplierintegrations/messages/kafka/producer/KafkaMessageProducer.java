package com.danielblanco.supplierintegrations.messages.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class KafkaMessageProducer {

    private final KafkaTemplate<String, String> producer;

    public KafkaMessageProducer(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }


    @Value(value = "${topic.name}")
    private String topicName;

    public void sendMessage(String messageInput) {
        StringBuilder message = new StringBuilder();
        message.append("IP80.238.9.179,");
        message.append(new Date().getTime());
        message.append(",SIGNIN_SUCCESS,");
        message.append("Will.Smith");

        producer.send(topicName, message.toString());
    }
}
