package com.hawking.bros.domain.mainService.sendService;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import com.hawking.bros.domain.mainService.sendService.feign.ServiceBFeignClient;
import com.hawking.bros.domain.mainService.sendService.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendServiceImpl implements SendService {

    private final ServiceBFeignClient serviceBFeignClient;
    private final MessageMapper messageMapper;

    @Override
    public void sendMessage(GisMeteoMessage gisMeteoMessage) {
        RsDtoMessageB rsDtoMessageB = messageMapper.mapMessageB(gisMeteoMessage);
        log.info("Отправлено сообщение в ServiceB {}", rsDtoMessageB);
        serviceBFeignClient.sendMessage(rsDtoMessageB);
    }
}
