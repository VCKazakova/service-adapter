package com.hawking.bros.domain.mainService.sendService.mapper;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;

public interface MessageBFactory {

    RsDtoMessageB create(GisMeteoMessage gisMeteoMessage);

}
