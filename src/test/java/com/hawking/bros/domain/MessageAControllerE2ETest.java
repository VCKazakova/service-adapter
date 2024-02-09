package com.hawking.bros.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoCoordinates;
import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.feign.GisMeteoFeignClient;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import com.hawking.bros.domain.mainService.sendService.feign.ServiceBFeignClient;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("MessageAControllerE2E should:")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageAControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GisMeteoFeignClient gisMeteoFeignClient;
    @MockBean
    private ServiceBFeignClient serviceBFeignClient;

    @Test
    @DisplayName("успешно обогащать и отправлять сообщение в другой сервис")
    public void e2eSuccessTest() {
        GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(11)));
        when(gisMeteoFeignClient.getWeather(anyString(), anyDouble(), anyDouble())).thenReturn(gisMeteoMessage);
        doNothing().when(serviceBFeignClient).sendMessage(any());

        RqDtoMessageA correctRqDtoMessageA =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));

        ResponseEntity<String> response =
                restTemplate.postForEntity("/v1/messages", correctRqDtoMessageA, String.class);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @DisplayName("выбрасывать ошибку, если от gismeteo пришла ошибка")
    public void e2eThrowExceptionIfGisMeteoNotAvailableTest() {
        when(gisMeteoFeignClient.getWeather(anyString(), anyDouble(), anyDouble())).thenThrow(FeignException.class);
        doNothing().when(serviceBFeignClient).sendMessage(any());

        RqDtoMessageA correctRqDtoMessageA =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));

        ResponseEntity<String> response =
                restTemplate.postForEntity("/v1/messages", correctRqDtoMessageA, String.class);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    @DisplayName("выбрасывать ошибку, если не удалось создать сообщение B")
    public void e2eThrowExceptionIfNotCreateBTest() {
        GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(null));
        when(gisMeteoFeignClient.getWeather(anyString(), anyDouble(), anyDouble())).thenReturn(gisMeteoMessage);
        doNothing().when(serviceBFeignClient).sendMessage(any());

        RqDtoMessageA correctRqDtoMessageA =
                new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));

        ResponseEntity<String> response =
                restTemplate.postForEntity("/v1/messages", correctRqDtoMessageA, String.class);
        assertEquals(500, response.getStatusCode().value());
    }

}
