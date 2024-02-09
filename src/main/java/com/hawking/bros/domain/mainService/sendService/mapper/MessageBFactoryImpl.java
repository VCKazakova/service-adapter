package com.hawking.bros.domain.mainService.sendService.mapper;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import com.hawking.bros.domain.mainService.richService.feign.dto.Temperature;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageBFactoryImpl implements MessageBFactory {

    public RsDtoMessageB create(GisMeteoMessage gisMeteoMessage) {
        return Optional.ofNullable(gisMeteoMessage)
                .map(GisMeteoMessage::getTemperature)
                .map(Temperature::getAir)
                .map(air -> new RsDtoMessageB("Привет", LocalDateTime.now(), (int) air.getC()))
                .orElseThrow(() -> new RuntimeException("Некорректный ответ от GisMeteo"));
    }

}
