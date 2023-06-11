package com.danielblanco.supplierintegrations.messages.kafka.consumer;

import com.danielblanco.supplierintegrations.domain.services.impl.HackerDetectorImpl;
import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class KafkaMessageConsumerTest {


    @Mock
    private HackerDetectorImpl hackerDetector;

    @Mock
    private KafkaMessageProducer kafkaMessageProducer;

    private KafkaMessageConsumer kafkaMessageConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        kafkaMessageConsumer = new KafkaMessageConsumer(hackerDetector, kafkaMessageProducer);
    }

    @Test
    public void consumerWithLoginMessageTest() {
        String message = "LOGIN:example-message";
        String banAccountMessage = "BAN_ACCOUNT:example-ban-message";

        when(hackerDetector.parseLine(anyString())).thenReturn("example-ban-message");

        kafkaMessageConsumer.consumer(message);

        verify(hackerDetector).parseLine("example-message");
        verify(kafkaMessageProducer).sendMessage(banAccountMessage);
    }



}