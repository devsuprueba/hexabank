package com.hexabank.client.web;

import com.hexabank.client.application.dto.ClientDto;
import com.hexabank.client.application.mapper.ClientMapper;
import com.hexabank.client.application.service.ClientService;
import com.hexabank.client.infra.persistence.ClientEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@jakarta.validation.Valid @RequestBody ClientDto dto) {
        ClientEntity created = service.create(ClientMapper.toEntity(dto));
        ClientDto out = ClientMapper.toDto(created);
        HttpHeaders headers = new HttpHeaders();
        // Build Location header based on current request
        java.net.URI location = org.springframework.web.servlet.support.ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(out, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClientDto> list() {
        return service.getAll().stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> get(@PathVariable Long id) {
        java.util.Optional<com.hexabank.client.infra.persistence.ClientEntity> opt = service.getById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(ClientMapper.toDto(opt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(
        @PathVariable Long id,
        @jakarta.validation.Valid @RequestBody ClientDto dto) {
        ClientEntity updated = service.update(
                id,
                ClientMapper.toEntity(dto)
        );
        return ResponseEntity.ok(ClientMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
