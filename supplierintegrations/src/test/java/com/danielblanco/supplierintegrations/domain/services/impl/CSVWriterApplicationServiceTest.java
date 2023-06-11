package com.danielblanco.supplierintegrations.domain.services.impl;

import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CSVWriterApplicationServiceTest {
}