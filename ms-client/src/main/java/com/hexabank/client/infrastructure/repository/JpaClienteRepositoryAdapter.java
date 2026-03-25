package com.hexabank.client.infrastructure.repository;

import com.hexabank.client.application.port.ClienteRepositoryPort;
import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.infrastructure.entity.ClienteEntity;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * JPA adapter that maps domain Cliente to ClienteEntity and delegates to Spring Data repository.
 */
@Repository
@Profile("!test")
public class JpaClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository repository;

    public JpaClienteRepositoryAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        ClienteEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Cliente toDomain(ClienteEntity e) {
        if (e == null) {
            return null;
        }
        return new Cliente(e.getId(), e.getNombre());
    }

    private ClienteEntity toEntity(Cliente c) {
        ClienteEntity e = new ClienteEntity();
        e.setId(c.getId());
        e.setNombre(c.getNombre());
        return e;
    }

}
