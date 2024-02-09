package com.hawking.bros.domain.mainService.sendService;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import com.hawking.bros.domain.mainService.sendService.feign.ServiceBFeignClient;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import com.hawking.bros.domain.mainService.sendService.mapper.MessageMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SendService should:")
class SendServiceImplTest {

    @InjectMocks
    private SendServiceImpl sendService;

    @Mock
    private ServiceBFeignClient serviceBFeignClient;

    @Mock
    private MessageMapper messageMapper;

    private final GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(12.5)));
    private final RsDtoMessageB rsDtoMessageB = new RsDtoMessageB("Привет", LocalDateTime.now(), 12);

    @Test
    @DisplayName("успешно отправлять сообщение в ServiceB")
    public void sendMessageSuccessTest() {
        when(messageMapper.mapMessageB(gisMeteoMessage)).thenReturn(rsDtoMessageB);
        doNothing().when(serviceBFeignClient).sendMessage(rsDtoMessageB);

        sendService.sendMessage(gisMeteoMessage);

        verify(serviceBFeignClient, times(1)).sendMessage(rsDtoMessageB);
        verify(messageMapper, times(1)).mapMessageB(gisMeteoMessage);
    }

}