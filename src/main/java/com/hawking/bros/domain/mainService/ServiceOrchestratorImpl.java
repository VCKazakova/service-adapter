package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.Ing;
import com.hawking.bros.domain.dto.RqDtoMessageA;
import com.hawking.bros.domain.mainService.richService.RichService;
import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.sendService.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceOrchestratorImpl implements ServiceOrchestrator {

    private final RichService richService;
    private final SendService sendService;

    @Override
    public void handleMessage(RqDtoMessageA rqDtoMessageA) {
        log.info("Принято сообщение {}", rqDtoMessageA);
        if (Ing.ru.equals(rqDtoMessageA.getIng())) {
            GisMeteoMessage gisMeteoMessage = richService.richMessage(rqDtoMessageA);
            sendService.sendMessage(gisMeteoMessage);
        }
    }
}
