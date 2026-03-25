package com.hexabank.client.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hexabank.client.application.port.EventPublisherPort;
import com.hexabank.client.application.service.ClienteService;
import com.hexabank.client.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateClienteUseCaseTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private EventPublisherPort publisher;

    @InjectMocks
    private CreateClienteUseCase useCase;

    private Cliente sample;

    @BeforeEach
    void setup() {
        sample = new Cliente(null, "Juan");
    }

    @Test
    void executeHappyPathPublishesAndReturnsCreated() {
        Cliente created = new Cliente(1L, "Juan");
        when(clienteService.create(sample)).thenReturn(created);

        Cliente result = useCase.execute(sample);

        assertEquals(created.getId(), result.getId());
        assertEquals(created.getNombre(), result.getNombre());
        verify(publisher).publish("cliente.created", created);
    }

    @Test
    void executeNullClientThrows() {
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
    }

}
