package com.danielblanco.supplierintegrations.application.rest;

import com.danielblanco.supplierintegrations.application.controller.LogController;
import com.danielblanco.supplierintegrations.domain.services.CSVWriterService;
import com.danielblanco.supplierintegrations.messages.kafka.producer.KafkaMessageProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class LogControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CSVWriterService csvWriterService;

    @Mock
    private KafkaMessageProducer kafkaMessageProducer;

    @InjectMocks
    private LogController logController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(logController).build();
    }

    @Test
    public void createLogValidTotalLinesTest() throws Exception {
        String totalLines = "100";
        String filePath = "/path/to/log.csv";

        when(csvWriterService.generateActivityLogFile(totalLines)).thenReturn(filePath);

        mockMvc.perform(get("/create-log")
                        .param("total_line", totalLines))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Create CSV! " + filePath));
    }

    @Test
    public void createLogInvalidTotalLinesTest() throws Exception {
        String totalLines = "abc";

        mockMvc.perform(get("/create-log")
                        .param("total_line", totalLines))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: total_lines must be an integer"));
    }

    @Test
    public void testLogin() throws Exception {
        String requestLogin = "example-request-login";

        mockMvc.perform(post("/login")
                        .param("requestLogin", requestLogin)
                        .content(requestLogin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Message send! " + requestLogin));

    }
}