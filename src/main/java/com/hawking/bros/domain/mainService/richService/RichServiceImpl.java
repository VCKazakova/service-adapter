package com.hawking.bros.domain.mainService.richService;

import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import org.springframework.stereotype.Service;

@Service
public class RichServiceImpl implements RichService {

    @Override
    public MessageB richMessage(MessageA messageA) {
        return null;
    }
}
