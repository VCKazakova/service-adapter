package com.hawking.bros.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    @JsonProperty("latitude")
    @NotBlank(message = "Широта должна быть указана")
    private String latitude;

    @JsonProperty("longitude")
    @NotBlank(message = "Долгота должна быть указана")
    private String longitude;

}
