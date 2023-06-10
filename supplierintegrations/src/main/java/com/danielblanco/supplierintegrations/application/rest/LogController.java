package com.danielblanco.supplierintegrations.application.rest;

import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    private final KafkaMessageProducer kafkaMessageProducer;

    public LogController(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @GetMapping("/create-log")
    public String createLog() {
        kafkaMessageProducer.sendMessage("Prueba" + (int) (Math.random() * 1000 + 1));
        return "¡Producido mensaje en topic!";
    }

}
