package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.mainService.richService.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import com.hawking.bros.domain.mainService.richService.dto.Temperature;
import com.hawking.bros.domain.mainService.richService.dto.TemperatureDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MessageMapper should:")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageMapperTest {

    private MessageMapper messageMapper;
    private GisMeteoMessage gisMeteoMessage;

    @BeforeAll
    public void init() {
        messageMapper = new MessageMapper();
        gisMeteoMessage = new GisMeteoMessage(new Temperature(new TemperatureDetails(16.9)));
    }

    @Test
    @DisplayName("маппить сообщение из гисметео в нужный формат")
    public void mapMessageBTest() {
        MessageB messageB = messageMapper.mapMessageB(gisMeteoMessage);
        assertEquals("Привет", messageB.getTxt());
        assertEquals(16, messageB.getCurrentTemp());
        assertNotNull(messageB.getCreatedDt());
    }

    @Test
    @DisplayName("возвращать пустое сообщение, если в сообщении нет данных")
    public void mapMessageBReturnEmptyMessageTest() {
        MessageB messageB = messageMapper.mapMessageB(new GisMeteoMessage());
        assertTrue(isEmpty(messageB.getTxt(), messageB.getCreatedDt(), messageB.getCurrentTemp()));
    }

    private boolean isEmpty(String txt, LocalDateTime ldt, int currentTemp) {
        return txt == null && ldt == null && currentTemp == 0;
    }

}