package com.hawking.bros.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RqDtoMessageA {

    @JsonProperty(value = "msg", required = true)
    @NotBlank(message = "Сообщение не должно быть пустым")
    String msg;

    @NotNull
    @JsonProperty(value = "Ing", required = true)
    Ing Ing;

    @Valid
    @JsonProperty(value = "coordinates")
    RqDtoCoordinates rqDtoCoordinates;

    public String getLatitude() {
        return rqDtoCoordinates.getLatitude();
    }

    public String getLongitude() {
        return rqDtoCoordinates.getLongitude();
    }
}
