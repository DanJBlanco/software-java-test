package com.danielblanco.supplierintegrations.messages.kafka.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class KafkaMessageProducerTest {

    private KafkaMessageProducer kafkaMessageProducer;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        kafkaMessageProducer = new KafkaMessageProducer(kafkaTemplate);
    }
    @Test
    public void sendMessageTest() {
        String message = "example-message";
        String topicName = "example-topic";

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> topicNameCaptor = ArgumentCaptor.forClass(String.class);

        kafkaMessageProducer.sendMessage(message);

        verify(kafkaTemplate).send(topicNameCaptor.capture(), messageCaptor.capture());
        String capturedMessage = messageCaptor.getValue();

        assertEquals(message, capturedMessage);
    }
}