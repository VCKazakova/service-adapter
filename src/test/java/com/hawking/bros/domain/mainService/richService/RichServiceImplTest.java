package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.Coordinates;
import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import com.hawking.bros.domain.mainService.richService.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.dto.TemperatureDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RichServiceImplTest {

    @InjectMocks
    private RichServiceImpl richService;

    @Mock
    private GisMeteoFeignClient gisMeteoFeignClient;

    @Mock
    private MessageMapper messageMapper;

    private GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(2.1)));

    @Test
    public void richMessageTest() {
        when(gisMeteoFeignClient.getWeather(null, 55.6d, 33.8d)).thenReturn(gisMeteoMessage);
        when(messageMapper.mapMessageB(gisMeteoMessage)).thenReturn(new MessageB("Привет", LocalDateTime.now(), 2));

        MessageB messageB = richService.richMessage(new MessageA("Привет", "ru", new Coordinates("55.6", "33.8")));

        verify(gisMeteoFeignClient, times(1)).getWeather(null, 55.6d, 33.8d);
        verify(messageMapper, times(1)).mapMessageB(gisMeteoMessage);

        Assertions.assertEquals(2, messageB.getCurrentTemp());
    }

}