package com.hawking.bros.domain.mainService.richService.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {

    @JsonProperty("air")
    private TemperatureDetails air;

}
