package com.hawking.bros.domain;

import com.hawking.bros.domain.dto.MessageA;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/messages")
public interface MessageAController {

    @PostMapping("/messageA")
    String richMessage(@RequestBody @Valid MessageA messageA);

}
