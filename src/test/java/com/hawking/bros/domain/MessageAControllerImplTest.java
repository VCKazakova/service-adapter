package com.hawking.bros.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoCoordinates;
import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.ServiceOrchestrator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("MessageAController should:")
@WebMvcTest(controllers = MessageAControllerImpl.class)
class MessageAControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceOrchestrator serviceOrchestrator;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("обработать сообщение, если переданы корректные данные")
    public void richMessageTest() throws Exception {
        RqDtoMessageA correctRqDtoMessageA =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));

        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(correctRqDtoMessageA)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан msg")
    public void richMessageThrowsValidationExceptionWithoutMsgTest() throws Exception {
        RqDtoMessageA rqDtoMessageAWithoutMsg = new RqDtoMessageA("", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));

        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutMsg)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("выбросить исключение, если не передан ing")
    public void richMessageThrowsValidationExceptionWithoutIngTest() throws Exception {
        RqDtoMessageA rqDtoMessageAWithoutIng =
                new RqDtoMessageA("Привет", null, new RqDtoCoordinates("54.35", "52.52"));

        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutIng)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан latitude")
    public void richMessageThrowsValidationExceptionWithoutLatitudeTest() throws Exception {
        RqDtoMessageA rqDtoMessageAWithoutLatitude =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates(null, "52.52"));

        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutLatitude)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("выбросить исключение, если не передан longitude")
    public void richMessageThrowsValidationExceptionWithoutLongitudeTest() throws Exception {
        RqDtoMessageA rqDtoMessageAWithoutLongitude =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", null));

        mockMvc.perform(post("/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rqDtoMessageAWithoutLongitude)))
                .andExpect(status().isBadRequest());
    }

}