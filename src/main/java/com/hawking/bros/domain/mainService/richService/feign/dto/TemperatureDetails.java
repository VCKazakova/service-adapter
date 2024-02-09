package com.hawking.bros.domain.mainService.richService.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TemperatureDetails {

    @JsonProperty("C")
    double c;

}
