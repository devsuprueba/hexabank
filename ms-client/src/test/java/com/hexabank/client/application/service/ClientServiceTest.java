package com.hexabank.client.application.service;

import com.hexabank.client.application.exception.DuplicateIdentificationException;
import com.hexabank.client.infra.persistence.ClientEntity;
import com.hexabank.client.infra.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    private ClientEntity sample;

    private static final String EXISTING_ID = "ID123";
    private static final String NEW_ID = "ID999";

    @BeforeEach
    void setUp() {
        sample = new ClientEntity();
        sample.setId(1L);
        sample.setName("Alice");
        sample.setIdentification(EXISTING_ID);
    }

    @Test
    void createWhenIdentificationExistsThrows() {
        when(repository.findByIdentification(EXISTING_ID)).thenReturn(Optional.of(sample));

        ClientEntity toCreate = new ClientEntity();
        toCreate.setIdentification(EXISTING_ID);

        assertThrows(DuplicateIdentificationException.class, () -> service.create(toCreate));
        verify(repository, never()).save(any());
    }

    @Test
    void createSuccessSavesAndReturns() {
        when(repository.findByIdentification(NEW_ID)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(inv -> {
            ClientEntity arg = inv.getArgument(0);
            arg.setId(5L);
            return arg;
        });

        ClientEntity toCreate = new ClientEntity();
        toCreate.setIdentification(NEW_ID);
        toCreate.setName("Bob");

        ClientEntity created = service.create(toCreate);
        assertNotNull(created.getId());
        assertEquals(NEW_ID, created.getIdentification());
        verify(repository).save(any());
    }

    @Test
    void updateWhenNotFoundThrows() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ClientEntity changes = new ClientEntity();
        changes.setName("X");

        assertThrows(IllegalStateException.class, () -> service.update(99L, changes));
    }

    @Test
    void updateWhenIdentificationConflictsThrowsDuplicate() {
        ClientEntity existing = new ClientEntity();
        existing.setId(1L);
        existing.setIdentification("OLD");

        ClientEntity conflict = new ClientEntity();
        conflict.setId(2L);
        conflict.setIdentification("NEW");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.findByIdentification("NEW")).thenReturn(Optional.of(conflict));

        ClientEntity changes = new ClientEntity();
        changes.setIdentification("NEW");

        assertThrows(DuplicateIdentificationException.class, () -> service.update(1L, changes));
    }
}
