package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RichServiceImpl implements RichService {

    private final GisMeteoFeignClient gisMeteoFeignClient;
    private final MessageMapper messageMapper;

    @Value("${gisMeteo.token}")
    private String token;

    @Override
    public MessageB richMessage(MessageA messageA) {
        double latitude = Double.parseDouble(messageA.getCoordinates().getLatitude());
        double longitude = Double.parseDouble(messageA.getCoordinates().getLongitude());
        GisMeteoMessage gisMeteoMessage = gisMeteoFeignClient.getWeather(token, latitude, longitude);
        return messageMapper.mapMessageB(gisMeteoMessage);
    }
}
