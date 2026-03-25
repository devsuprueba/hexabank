package com.hexabank.client.application.usecase;

import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.application.service.ClienteService;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetClienteUseCase {

    private final ClienteService service;

    public GetClienteUseCase(ClienteService service) {
        this.service = service;
    }

    public Optional<Cliente> execute(Long id) {
        return service.findById(id);
    }
}
