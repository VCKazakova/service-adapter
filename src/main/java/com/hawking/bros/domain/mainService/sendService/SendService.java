package com.hawking.bros.domain.mainService.sendService;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;

public interface SendService {

    void sendMessage(GisMeteoMessage gisMeteoMessage);

}
