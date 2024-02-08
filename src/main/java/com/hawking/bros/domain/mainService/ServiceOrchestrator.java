package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.MessageA;

public interface ServiceOrchestrator {

    String handleMessage(MessageA messageA);

}
