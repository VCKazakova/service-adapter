package com.hawking.bros.domain.mainService.sendService.mapper;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MessageFactory should:")
@ExtendWith(MockitoExtension.class)
class MessageBFactoryImplTest {

    @InjectMocks
    private MessageBFactoryImpl messageBFactory;

    @Test
    @DisplayName("маппить сообщение из гисметео в нужный формат")
    public void mapMessageBTest() {
        GisMeteoMessage gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(16.9)));

        RsDtoMessageB rsDtoMessageB = messageBFactory.create(gisMeteoMessage);
        assertEquals("Привет", rsDtoMessageB.getTxt());
        assertEquals(16, rsDtoMessageB.getCurrentTemp());
        assertNotNull(rsDtoMessageB.getCreatedDt());
    }

    @Test
    @DisplayName("кидать исключение, если в сообщении нет данных")
    public void mapMessageBReturnEmptyMessageTest() {
        assertThrows(RuntimeException.class, () -> messageBFactory.create(new GisMeteoMessage(new Temperature())));
    }

}