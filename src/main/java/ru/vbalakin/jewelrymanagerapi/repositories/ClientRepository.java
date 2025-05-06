package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    List<ClientEntity> findAll();

    Optional<ClientEntity> findByName(String name);

    List<ClientEntity> id(UUID id);
}
