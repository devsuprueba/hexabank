package com.hexabank.client.application.service;

import com.hexabank.client.application.port.ClienteRepositoryPort;
import com.hexabank.client.domain.model.Cliente;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
