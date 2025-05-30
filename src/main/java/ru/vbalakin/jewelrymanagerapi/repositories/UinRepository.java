package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

import java.util.Optional;
import java.util.UUID;

public interface UinRepository extends JpaRepository<UinEntity, UUID> {
    Optional<UinEntity> findByClientId(UUID clientId);

    Optional<UinEntity> findByUin(String uin);
}
