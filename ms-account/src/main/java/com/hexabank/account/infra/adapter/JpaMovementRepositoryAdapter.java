package com.hexabank.account.infra.adapter;

import com.hexabank.account.application.port.MovementRepositoryPort;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.infra.MovementRepository;
import com.hexabank.account.infra.mapper.AccountPersistenceMapper;
import com.hexabank.account.infra.persistence.MovementEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaMovementRepositoryAdapter implements MovementRepositoryPort {

    private final MovementRepository repository;

    public JpaMovementRepositoryAdapter(MovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movement save(Movement movement) {
        MovementEntity me = AccountPersistenceMapper.toEntityMovement(movement);
        MovementEntity saved = repository.save(me);
        return AccountPersistenceMapper.toDomainMovement(saved);
    }

    @Override
    public Optional<Movement> findById(Long id) {
        return repository.findById(id).map(AccountPersistenceMapper::toDomainMovement);
    }

    @Override
    public List<Movement> findByAccountId(Long accountId) {
        return repository.findByAccountIdOrderByMovementDateDesc(accountId).stream()
                .map(AccountPersistenceMapper::toDomainMovement)
                .collect(Collectors.toList());
    }
}
