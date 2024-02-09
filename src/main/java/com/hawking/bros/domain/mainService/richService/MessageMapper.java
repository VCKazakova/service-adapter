package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.mainService.richService.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageMapper {

    public MessageB mapMessageB(GisMeteoMessage gisMeteoMessage) {
        return Optional.ofNullable(gisMeteoMessage)
                .flatMap(message -> Optional.ofNullable(message.getTemperature()))
                .flatMap(temperature -> Optional.ofNullable(temperature.getAir()))
                .map(air -> new MessageB("Привет", LocalDateTime.now(), (int) air.getC()))
                .orElse(new MessageB());
    }

}
