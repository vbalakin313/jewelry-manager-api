package ru.vbalakin.jewelrymanagerapi.services;

import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ClientService implements ru.vbalakin.jewelrymanagerapi.repositories.ClientService {
    @Override
    public List<ClientEntity> findAll() {
        return findAll().stream().collect(Collectors.toList());
    }

    @Override
    public boolean existsByUin_Uin(String uinUin) {
        return existsByUin_Uin(uinUin);
    }
}
