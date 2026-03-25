package com.hexabank.client.application.usecase;

import com.hexabank.client.domain.model.Cliente;
import org.springframework.stereotype.Service;

/**
 * Minimal use case class to establish application layer package.
 * Implementation is intentionally minimal for project setup.
 */
@Service
public class CreateClienteUseCase {

    public Cliente execute(Cliente cliente) {
        // In a full implementation this would orchestrate the creation flow.
        return cliente;
    }

}
