package com.danielblanco.supplierintegrations.infraestructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

class CSVReaderServiceImplTest {

    private CSVReaderServiceImpl csvReaderService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        csvReaderService = new CSVReaderServiceImpl();

    }

    @Test
    public void testReadCSVFileTest()  throws IOException{

        String csvFilePath = new ClassPathResource("test.csv").getFile().getPath() ;

        csvReaderService.readCSVFile(csvFilePath);

    }
}