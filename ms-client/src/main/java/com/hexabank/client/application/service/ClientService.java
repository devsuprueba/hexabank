package com.hexabank.client.application.service;

import com.hexabank.client.infra.persistence.ClientEntity;
import com.hexabank.client.infra.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientEntity create(ClientEntity entity) {
        // check duplicate identification
        if (entity.getIdentification() != null && repository.findByIdentification(entity.getIdentification()).isPresent()) {
            throw new com.hexabank.client.application.exception.DuplicateIdentificationException("identification already exists");
        }
        return repository.save(entity);
    }

    public Optional<ClientEntity> getById(Long id) {
        return repository.findById(id);
    }

    public List<ClientEntity> getAll() {
        return repository.findAll();
    }

    public ClientEntity update(Long id, ClientEntity changes) {
        return repository.findById(id).map(existing -> {
            // if identification is changing, ensure uniqueness
            String newIden = changes.getIdentification();
            if (newIden != null && !newIden.equals(existing.getIdentification())) {
                repository.findByIdentification(newIden).ifPresent(conflict -> {
                    throw new com.hexabank.client.application.exception.DuplicateIdentificationException("identification already exists");
                });
                existing.setIdentification(newIden);
            }
            existing.setName(changes.getName());
            existing.setGender(changes.getGender());
            existing.setAge(changes.getAge());
            existing.setAddress(changes.getAddress());
            existing.setPhone(changes.getPhone());
            existing.setStatus(changes.getStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalStateException("Client not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
