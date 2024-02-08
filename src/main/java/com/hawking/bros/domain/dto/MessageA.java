package com.hawking.bros.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageA {

    @JsonProperty("msg")
    @NotBlank(message = "Сообщение не должно быть пустым")
    private String msg;

    @JsonProperty("Ing")
    @NotBlank(message = "Язык должен быть указан")
    private String Ing;

    @JsonProperty("coordinates")
    @Valid
    private Coordinates coordinates;

}
