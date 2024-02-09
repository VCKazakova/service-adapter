package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoCoordinates;
import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.feign.GisMeteoFeignClient;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RichService should:")
class RichServiceImplTest {

    @InjectMocks
    private RichServiceImpl richService;

    @Mock
    private GisMeteoFeignClient gisMeteoFeignClient;

    @Test
    @DisplayName("успешно возвращать результат от gismeteo")
    public void richMessageTest() {
        GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(2.1)));
        RqDtoMessageA rqDtoMessageA = new RqDtoMessageA("Привет", Ing.ru, new RqDtoCoordinates("55.6", "33.8"));

        when(gisMeteoFeignClient.getWeather(null, 55.6d, 33.8d)).thenReturn(gisMeteoMessage);

        GisMeteoMessage result = richService.richMessage(rqDtoMessageA);

        verify(gisMeteoFeignClient, times(1)).getWeather(null, 55.6d, 33.8d);

        Assertions.assertEquals(2.1, result.getTemperature().getAir().getC());
    }

}