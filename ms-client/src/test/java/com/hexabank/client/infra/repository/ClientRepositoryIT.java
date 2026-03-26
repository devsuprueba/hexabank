package com.hexabank.client.infra.repository;

import com.hexabank.client.infra.persistence.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ClientRepositoryIT {

    @Autowired
    private ClientRepository repository;

    @Test
    void savingDuplicateIdentificationThrows() {
        ClientEntity a = new ClientEntity();
        a.setName("A");
        a.setIdentification("DUPLICATE");

        ClientEntity b = new ClientEntity();
        b.setName("B");
        b.setIdentification("DUPLICATE");

        repository.saveAndFlush(a);

        assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(b));
    }
}
