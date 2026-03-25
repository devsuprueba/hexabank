package com.hexabank.client.infrastructure.repository;

import com.hexabank.client.application.port.ClienteRepositoryPort;
import com.hexabank.client.domain.model.Cliente;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final Map<Long, Cliente> store = new HashMap<>();

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Cliente save(Cliente cliente) {
        store.put(cliente.getId(), cliente);
        return cliente;
    }
}
