package com.hexabank.client.web;

import com.hexabank.client.application.dto.ClientDto;
import com.hexabank.client.application.mapper.ClientMapper;
import com.hexabank.client.application.service.ClientService;
import com.hexabank.client.infra.persistence.ClientEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto dto, UriComponentsBuilder uriBuilder) {
        ClientEntity created = service.create(ClientMapper.toEntity(dto));
        ClientDto out = ClientMapper.toDto(created);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/api/clients/{id}").buildAndExpand(created.getId()).toUri());
        return new ResponseEntity<>(out, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClientDto> list() {
        return service.getAll().stream().map(ClientMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> get(@PathVariable Long id) {
        return service.getById(id).map(e -> ResponseEntity.ok(ClientMapper.toDto(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto dto) {
        try {
            ClientEntity updated = service.update(id, ClientMapper.toEntity(dto));
            return ResponseEntity.ok(ClientMapper.toDto(updated));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
