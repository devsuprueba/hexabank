package com.hexabank.client.application.usecase;

import com.hexabank.client.application.port.EventPublisherPort;
import com.hexabank.client.application.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeleteClienteUseCase {

    private static final Logger log = LoggerFactory.getLogger(DeleteClienteUseCase.class);

    private final ClienteService clienteService;
    private final EventPublisherPort publisher;

    public DeleteClienteUseCase(ClienteService clienteService, EventPublisherPort publisher) {
        this.clienteService = clienteService;
        this.publisher = publisher;
    }

    public void execute(Long id) {
        log.info("Start DeleteClienteUseCase for id={}", id);
        if (id == null) {
            throw new IllegalArgumentException("Id requerido");
        }
        clienteService.delete(id);
        publisher.publish("cliente.deleted", id);
        log.info("End DeleteClienteUseCase for id={}", id);
    }

}
