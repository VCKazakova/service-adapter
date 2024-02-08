package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.RichService;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import com.hawking.bros.domain.mainService.sendService.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceOrchestratorImpl implements ServiceOrchestrator {

    private final RichService richService;
    private final SendService sendService;

    @Override
    public String handleMessage(MessageA messageA) {
        if ("ru".equals(messageA.getIng())) {
            MessageB messageB = richService.richMessage(messageA);
            return sendService.sendMessage(messageB);
        }
        return null;
    }
}
