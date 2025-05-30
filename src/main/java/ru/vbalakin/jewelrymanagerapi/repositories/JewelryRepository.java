package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

import java.util.Optional;
import java.util.UUID;

public interface JewelryRepository extends JpaRepository<JewelryEntity, UUID> {
    JewelryEntity getById(UUID id);

    Optional<JewelryEntity> findByUin(UinEntity uin);

    void deleteByUin_Uin(String uin);
}

