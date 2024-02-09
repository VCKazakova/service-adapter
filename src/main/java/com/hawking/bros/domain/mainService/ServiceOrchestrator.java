package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.RqDtoMessageA;

public interface ServiceOrchestrator {

    void handleMessage(RqDtoMessageA rqDtoMessageA);

}
