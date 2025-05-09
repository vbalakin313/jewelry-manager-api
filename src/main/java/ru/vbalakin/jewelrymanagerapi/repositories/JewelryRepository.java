package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;

import java.util.UUID;

public interface JewelryRepository extends JpaRepository<JewelryEntity, UUID> {
    JewelryEntity getById(UUID id);
}
