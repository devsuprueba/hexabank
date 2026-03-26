package com.hexabank.account.infra.kafka;

import com.hexabank.account.application.usecase.ProcessClientEventUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class ClientEventListenerTest {

    @Mock
    private ProcessClientEventUseCase processor;

    @Test
    void onMessageDelegatesToProcessor() {
        final ClientEventListener listener = new ClientEventListener(processor);
        final ClientEvent event = new ClientEvent("CLIENT_CREATED", 123L, null);

        listener.onMessage(event);

        verify(processor).execute(event);
    }

    @Test
    void onMessageWithNullIsIgnored() {
        final ClientEventListener listener = new ClientEventListener(processor);

        listener.onMessage(null);

        verifyNoInteractions(processor);
    }
}
