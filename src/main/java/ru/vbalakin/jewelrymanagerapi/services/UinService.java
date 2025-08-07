package ru.vbalakin.jewelrymanagerapi.services;

import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

import java.util.Optional;
import java.util.UUID;

public abstract class UinService implements ru.vbalakin.jewelrymanagerapi.repositories.UinService {
    @Override
    public Optional<UinEntity> findByClientId(UUID clientId) {
        return Optional.empty();
    }

    @Override
    public Optional<UinEntity> findByUin(String uin) {
        return Optional.empty();
    }
}
