package com.hawking.bros.domain.mainService.richService.feign;

import com.hawking.bros.domain.mainService.richService.feign.dto.GisMeteoMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gismeteo", url = "${spring.cloud.openfeign.client.url.gismeteo}")
public interface GisMeteoFeignClient {

    @GetMapping("/v2/weather/current/")
    GisMeteoMessage getWeather(@RequestHeader("X-Gismeteo-Token") String token,
                               @RequestParam("latitude") double latitude,
                               @RequestParam("longitude") double longitude);

}
