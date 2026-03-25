package com.hexabank.client.application.usecase;

import com.hexabank.client.application.port.EventPublisherPort;
import com.hexabank.client.application.service.ClienteService;
import com.hexabank.client.domain.model.Cliente;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateClienteUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateClienteUseCase.class);

    private final ClienteService clienteService;
    private final EventPublisherPort publisher;

    public UpdateClienteUseCase(ClienteService clienteService, EventPublisherPort publisher) {
        this.clienteService = clienteService;
        this.publisher = publisher;
    }

    public Cliente execute(Cliente cliente) {
        log.info("Start UpdateClienteUseCase for id={}", cliente == null ? null : cliente.getId());
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente con id requerido");
        }
        Optional<Cliente> existing = clienteService.findById(cliente.getId());
        if (existing.isEmpty()) {
            throw new IllegalStateException("Cliente no encontrado: " + cliente.getId());
        }
        Cliente updated = clienteService.create(cliente);
        publisher.publish("cliente.updated", updated);
        log.info("End UpdateClienteUseCase for id={}", updated.getId());
        return updated;
    }

}
