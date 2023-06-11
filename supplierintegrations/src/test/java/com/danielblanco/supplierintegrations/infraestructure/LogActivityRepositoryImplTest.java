package com.danielblanco.supplierintegrations.infraestructure;

import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.mapper.LogActivityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

class LogActivityRepositoryImplTest {

    private LogActivityRepositoryImpl csvReaderService;

    @Mock
    LogActivityMapper logActivityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        csvReaderService = new LogActivityRepositoryImpl(logActivityMapper);

    }

    @Test
    public void testReadCSVFileTest()  throws IOException{

        String csvFilePath = new ClassPathResource("test.csv").getFile().getPath() ;

        csvReaderService.readCSVFile(csvFilePath, new LogActivity());

    }
}