package com.hawking.bros.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawking.bros.domain.dto.Coordinates;
import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.ServiceOrchestrator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("MessageAController should:")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers = MessageAControllerImpl.class)
class MessageAControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceOrchestrator serviceOrchestrator;
    @Autowired
    private ObjectMapper objectMapper;

    private MessageA correctMessageA;
    private MessageA messageAWithoutMsg;
    private MessageA messageAWithoutIng;
    private MessageA messageAWithoutLatitude;
    private MessageA messageAWithoutLongitude;

    @BeforeAll
    public void init() {
        correctMessageA = new MessageA("Привет", "ru", new Coordinates("54.35", "52.52"));
        messageAWithoutMsg = new MessageA("", "ru", new Coordinates("54.35", "52.52"));
        messageAWithoutIng = new MessageA("Привет", null, new Coordinates("54.35", "52.52"));
        messageAWithoutLatitude = new MessageA("Привет", "ru", new Coordinates(null, "52.52"));
        messageAWithoutLongitude = new MessageA("Привет", "ru", new Coordinates("54.35", null));
    }

    @Test
    @DisplayName("обработать сообщение, если переданы корректные данные")
    public void richMessageTest() throws Exception {
        mockMvc.perform(post("/v1/messages/messageA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(correctMessageA)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан msg")
    public void richMessageThrowsValidationExceptionWithoutMsgTest() throws Exception {
        mockMvc.perform(post("/v1/messages/messageA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageAWithoutMsg)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("выбросить исключение, если не передан ing")
    public void richMessageThrowsValidationExceptionWithoutIngTest() throws Exception {
        mockMvc.perform(post("/v1/messages/messageA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageAWithoutIng)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан latitude")
    public void richMessageThrowsValidationExceptionWithoutLatitudeTest() throws Exception {
        mockMvc.perform(post("/v1/messages/messageA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageAWithoutLatitude)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан longitude")
    public void richMessageThrowsValidationExceptionWithoutLongitudeTest() throws Exception {
        mockMvc.perform(post("/v1/messages/messageA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageAWithoutLongitude)))
                .andExpect(status().isBadRequest());
    }

}