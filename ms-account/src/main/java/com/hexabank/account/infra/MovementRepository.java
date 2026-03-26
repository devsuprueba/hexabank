package com.hexabank.account.infra;

import com.hexabank.account.infra.persistence.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByAccountIdOrderByMovementDateDesc(Long accountId);
}
