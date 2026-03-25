package com.hexabank.client.application.usecase;

import com.hexabank.client.application.port.EventPublisherPort;
import com.hexabank.client.application.service.ClienteService;
import com.hexabank.client.domain.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateClienteUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateClienteUseCase.class);

    private final ClienteService clienteService;
    private final EventPublisherPort publisher;

    public CreateClienteUseCase(ClienteService clienteService, EventPublisherPort publisher) {
        this.clienteService = clienteService;
        this.publisher = publisher;
    }

    public Cliente execute(Cliente cliente) {
        log.info("Start CreateClienteUseCase for id={}", cliente == null ? null : cliente.getId());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente requerido");
        }
        Cliente created = clienteService.create(cliente);
        // Publish event (topic name is example)
        publisher.publish("cliente.created", created);
        log.info("End CreateClienteUseCase for id={}", created.getId());
        return created;
    }

}
