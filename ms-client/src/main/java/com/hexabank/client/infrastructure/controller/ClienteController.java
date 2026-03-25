package com.hexabank.client.infrastructure.controller;

import com.hexabank.client.application.usecase.CreateClienteUseCase;
import com.hexabank.client.application.usecase.DeleteClienteUseCase;
import com.hexabank.client.application.usecase.GetAllClientesUseCase;
import com.hexabank.client.application.usecase.GetClienteUseCase;
import com.hexabank.client.application.usecase.UpdateClienteUseCase;
import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.infrastructure.controller.dto.ClientRequestDTO;
import com.hexabank.client.infrastructure.controller.dto.ClientResponseDTO;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private final CreateClienteUseCase createUseCase;
    private final UpdateClienteUseCase updateUseCase;
    private final DeleteClienteUseCase deleteUseCase;
    private final GetClienteUseCase getClienteUseCase;
    private final GetAllClientesUseCase getAllUseCase;

    public ClienteController(CreateClienteUseCase createUseCase,
            UpdateClienteUseCase updateUseCase,
            DeleteClienteUseCase deleteUseCase,
            GetClienteUseCase getClienteUseCase,
            GetAllClientesUseCase getAllUseCase) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getClienteUseCase = getClienteUseCase;
        this.getAllUseCase = getAllUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientRequestDTO request) {
        log.info("POST /api/clientes - request={}", request);
        Cliente toCreate = ClienteMapper.toDomain(request, null);
        Cliente created = createUseCase.execute(toCreate);
        ClientResponseDTO response = ClienteMapper.toResponse(created);
        URI location = URI.create(String.format("/api/clientes/%d", response.getId()));
        log.info("Created cliente id={}", response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAll() {
        log.info("GET /api/clientes");
        List<ClientResponseDTO> list = getAllUseCase.execute().stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable Long id) {
        Optional<Cliente> opt = getClienteUseCase.execute(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cliente c = opt.get();
        ClientResponseDTO dto = ClienteMapper.toResponse(c);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO request) {
        log.info("PUT /api/clientes/{} - request={}", id, request);
        Cliente toUpdate = ClienteMapper.toDomain(request, id);
        Cliente updated = updateUseCase.execute(toUpdate);
        return ResponseEntity.ok(ClienteMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/clientes/{}", id);
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
