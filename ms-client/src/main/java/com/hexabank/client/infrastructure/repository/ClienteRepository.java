package com.hexabank.client.infrastructure.repository;

import com.hexabank.client.infrastructure.entity.ClienteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query(value = "SELECT * FROM cliente WHERE id = :id", nativeQuery = true)
    Optional<ClienteEntity> findByIdNative(Long id);

}
