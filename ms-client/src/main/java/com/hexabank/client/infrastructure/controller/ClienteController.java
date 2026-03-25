package com.hexabank.client.infrastructure.controller;

import com.hexabank.client.application.usecase.GetClienteUseCase;
import com.hexabank.client.application.dto.ClienteDTO;
import com.hexabank.client.domain.model.Cliente;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final GetClienteUseCase getClienteUseCase;

    public ClienteController(GetClienteUseCase getClienteUseCase) {
        this.getClienteUseCase = getClienteUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        Optional<Cliente> opt = getClienteUseCase.execute(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cliente c = opt.get();
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        return ResponseEntity.ok(dto);
    }
}
