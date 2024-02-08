package com.hawking.bros.domain.mainService;

import com.hawking.bros.domain.dto.Coordinates;
import com.hawking.bros.domain.dto.MessageA;
import com.hawking.bros.domain.mainService.richService.RichService;
import com.hawking.bros.domain.mainService.richService.dto.MessageB;
import com.hawking.bros.domain.mainService.sendService.SendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ServiceOrchestrator should:")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceOrchestratorImplTest {

    @InjectMocks
    private ServiceOrchestratorImpl serviceOrchestrator;

    @Mock
    private RichService richService;

    @Mock
    private SendService sendService;

    private MessageA messageAWithRu;
    private MessageB messageB;
    private MessageA messageAWithEn;

    @BeforeAll
    public void init() {
        messageAWithRu = new MessageA("Привет", "ru", new Coordinates("54.35", "52.52"));
        messageB = new MessageB("Привет", LocalDateTime.now(), 28);
        messageAWithEn = new MessageA("Привет", "en", new Coordinates("54.35", "52.52"));
    }

    @Test
    @DisplayName("обработать и отправить сообщение")
    public void handleMessageTest() {
        when(richService.richMessage(messageAWithRu)).thenReturn(messageB);
        when(sendService.sendMessage(messageB)).thenReturn("ok");

        String result = serviceOrchestrator.handleMessage(messageAWithRu);

        verify(richService, times(1)).richMessage(messageAWithRu);
        verify(sendService, times(1)).sendMessage(messageB);
        Assertions.assertEquals("ok", result);
    }

    @Test
    @DisplayName("не обрабатывать и не отправлять сообщение")
    public void handleMessageWithEnIngTest() {

        String result = serviceOrchestrator.handleMessage(messageAWithEn);

        verify(richService, times(0)).richMessage(messageAWithEn);
        verify(sendService, times(0)).sendMessage(any());
        Assertions.assertNull(result);
    }

}