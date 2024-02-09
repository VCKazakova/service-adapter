package com.hawking.bros.domain.mainService.sendService.mapper;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.feign.dto.TemperatureDetails;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MessageMapper should:")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageMapperImplTest {

    private MessageMapper messageMapper;
    private GisMeteoMessage gisMeteoMessage;

    @BeforeAll
    public void init() {
        messageMapper = new MessageMapperImpl();
        gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(16.9)));
    }

    @Test
    @DisplayName("маппить сообщение из гисметео в нужный формат")
    public void mapMessageBTest() {
        RsDtoMessageB rsDtoMessageB = messageMapper.mapMessageB(gisMeteoMessage);
        assertEquals("Привет", rsDtoMessageB.getTxt());
        assertEquals(16, rsDtoMessageB.getCurrentTemp());
        assertNotNull(rsDtoMessageB.getCreatedDt());
    }

    @Test
    @DisplayName("кидать исключение, если в сообщении нет данных")
    public void mapMessageBReturnEmptyMessageTest() {
        assertThrows(RuntimeException.class, () -> messageMapper.mapMessageB(new GisMeteoMessage(new Temperature())));
    }

}