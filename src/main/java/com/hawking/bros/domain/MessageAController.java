package com.hawking.bros.domain;

import com.hawking.bros.domain.dto.RqDtoMessageA;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/messages")
public interface MessageAController {

    @PostMapping
    void richMessage(@RequestBody @Valid RqDtoMessageA rqDtoMessageA);

}
