package com.hawking.bros.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class RqDtoCoordinates {

    @JsonProperty(value = "latitude", required = true)
    @NotBlank(message = "Широта должна быть указана")
    String latitude;

    @JsonProperty(value = "longitude", required = true)
    @NotBlank(message = "Долгота должна быть указана")
    String longitude;

}
