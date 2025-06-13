package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;

import java.util.UUID;

public interface PreciousMetalRepository extends JpaRepository<PreciousMetalEntity, UUID> {
    PreciousMetalEntity getById(UUID id);

}
