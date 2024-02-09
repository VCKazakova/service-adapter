package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;

public interface RichService {

    GisMeteoMessage richMessage(RqDtoMessageA rqDtoMessageA);

}
