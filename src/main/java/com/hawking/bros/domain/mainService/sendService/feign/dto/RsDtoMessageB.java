package com.hawking.bros.domain.mainService.sendService.feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class RsDtoMessageB {

    @NotBlank
    String txt;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime createdDt;

    @NotBlank
    Integer currentTemp;

}
