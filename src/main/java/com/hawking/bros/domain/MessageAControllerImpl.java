package com.hawking.bros.domain;

import com.hawking.bros.domain.dto.MessageA;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageAControllerImpl implements MessageAController {


    @Override
    public String richMessage(MessageA messageA) {
        return null;
    }

}
