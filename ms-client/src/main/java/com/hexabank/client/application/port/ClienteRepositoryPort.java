package com.hexabank.client.application.port;

import com.hexabank.client.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Optional<Cliente> findById(Long id);

    Cliente save(Cliente cliente);

    void deleteById(Long id);

    List<Cliente> findAll();

}
