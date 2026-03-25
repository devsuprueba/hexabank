package com.hexabank.client.application.usecase;

import com.hexabank.client.application.service.ClienteService;
import com.hexabank.client.domain.model.Cliente;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientesUseCase {

    private final ClienteService service;

    public GetAllClientesUseCase(ClienteService service) {
        this.service = service;
    }

    public List<Cliente> execute() {
        return service.findAll();
    }

}
