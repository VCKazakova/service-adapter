package com.hawking.bros.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoCoordinates;
import com.hawking.bros.domain.dto.RqDtoMessageA;
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

    private RqDtoMessageA correctRqDtoMessageA;
    private RqDtoMessageA rqDtoMessageAWithoutMsg;
    private RqDtoMessageA rqDtoMessageAWithoutIng;
    private RqDtoMessageA rqDtoMessageAWithoutLatitude;
    private RqDtoMessageA rqDtoMessageAWithoutLongitude;

    @BeforeAll
    public void init() {
        correctRqDtoMessageA = new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));
        rqDtoMessageAWithoutMsg = new RqDtoMessageA("", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));
        rqDtoMessageAWithoutIng = new RqDtoMessageA("Привет", null, new RqDtoCoordinates("54.35", "52.52"));
        rqDtoMessageAWithoutLatitude = new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates(null, "52.52"));
        rqDtoMessageAWithoutLongitude = new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", null));
    }

    @Test
    @DisplayName("обработать сообщение, если переданы корректные данные")
    public void richMessageTest() throws Exception {
        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(correctRqDtoMessageA)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан msg")
    public void richMessageThrowsValidationExceptionWithoutMsgTest() throws Exception {
        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutMsg)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("выбросить исключение, если не передан ing")
    public void richMessageThrowsValidationExceptionWithoutIngTest() throws Exception {
        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutIng)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан latitude")
    public void richMessageThrowsValidationExceptionWithoutLatitudeTest() throws Exception {
        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutLatitude)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан longitude")
    public void richMessageThrowsValidationExceptionWithoutLongitudeTest() throws Exception {
        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutLongitude)))
                .andExpect(status().isBadRequest());
    }

}