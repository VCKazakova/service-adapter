package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.feign.GisMeteoFeignClient;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RichServiceImpl implements RichService {

    private final GisMeteoFeignClient gisMeteoFeignClient;

    @Value("${gisMeteo.token}")
    private String token;

    @Override
    public GisMeteoMessage richMessage(RqDtoMessageA rqDtoMessageA) {
        double latitude = Double.parseDouble(rqDtoMessageA.getLatitude());
        double longitude = Double.parseDouble(rqDtoMessageA.getLongitude());
        log.info("Отправлено сообщение gisMeteo с параметрами {} {}", latitude, longitude);
        GisMeteoMessage weather = gisMeteoFeignClient.getWeather(token, latitude, longitude);
        log.info("Принято сообщение от gisMeteo {}", weather);
        return weather;
    }
}
