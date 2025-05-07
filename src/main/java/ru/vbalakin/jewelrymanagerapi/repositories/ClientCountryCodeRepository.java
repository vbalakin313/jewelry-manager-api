package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;

import java.util.UUID;

public interface ClientCountryCodeRepository extends JpaRepository<ClientCountryCodeEntity, UUID> {
}
