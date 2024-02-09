package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoCoordinates;
import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.RichService;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import com.hawking.bros.domain.mainService.sendService.SendService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ServiceOrchestrator should:")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceOrchestratorImplTest {

    @InjectMocks
    private ServiceOrchestratorImpl serviceOrchestrator;

    @Mock
    private RichService richService;

    @Mock
    private SendService sendService;

    private RqDtoMessageA rsDtoMessageWithRu;
    private RqDtoMessageA rqDtoMessageAWithEn;
    private GisMeteoMessage gisMeteoMessage;

    @BeforeAll
    public void init() {
        rsDtoMessageWithRu = new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("54.35", "52.52"));
        rqDtoMessageAWithEn = new RqDtoMessageA("Привет", Ing.en, new RqDtoCoordinates("54.35", "52.52"));
        gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(12.5)));
    }

    @Test
    @DisplayName("обработать и отправить сообщение")
    public void handleMessageTest() {
        when(richService.richMessage(rsDtoMessageWithRu)).thenReturn(gisMeteoMessage);
        doNothing().when(sendService).sendMessage(gisMeteoMessage);

        serviceOrchestrator.handleMessage(rsDtoMessageWithRu);

        verify(richService, times(1)).richMessage(rsDtoMessageWithRu);
        verify(sendService, times(1)).sendMessage(gisMeteoMessage);
    }

    @Test
    @DisplayName("не обрабатывать и не отправлять сообщение")
    public void handleMessageWithEnIngTest() {
        verify(richService, times(0)).richMessage(rqDtoMessageAWithEn);
        verify(sendService, times(0)).sendMessage(any());
    }

}