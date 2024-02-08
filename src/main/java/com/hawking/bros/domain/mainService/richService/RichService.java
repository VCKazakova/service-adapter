package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;

public interface RichService {

    MessageB richMessage(MessageA messageA);

}
