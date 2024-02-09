package com.hawking.bros.domain.mainService.richService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GisMeteoMessage {

    @JsonProperty("temperature")
    private Temperature temperature;


}
