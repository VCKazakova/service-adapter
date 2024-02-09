package com.hawking.bros.domain.mainService.sendService.feign;

import com.hawking.bros.domain.mainService.sendService.feign.dto.RsDtoMessageB;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "serviceB", url = "${spring.cloud.openfeign.client.url.serviceB}")
public interface ServiceBFeignClient {

    @PostMapping("/v1/messages/")
    void sendMessage(@RequestBody @Valid RsDtoMessageB rsDtoMessageB);

}
