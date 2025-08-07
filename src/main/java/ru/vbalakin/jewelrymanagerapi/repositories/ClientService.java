package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

import java.util.List;
import java.util.UUID;

public interface ClientService extends JpaRepository<ClientEntity, UUID> {

    List<ClientEntity> findAll();

    boolean existsByUin_Uin(String uinUin);
}
