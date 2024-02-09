package com.hawking.bros.domain;

import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.ServiceOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageAControllerImpl implements MessageAController {

    private final ServiceOrchestrator serviceOrchestrator;

    @Override
    public String richMessage(MessageA messageA) {
        return serviceOrchestrator.handleMessage(messageA);
    }

}
