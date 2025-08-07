package ru.vbalakin.jewelrymanagerapi.services;

import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;

import java.util.UUID;

public abstract class PreciousMetalService implements ru.vbalakin.jewelrymanagerapi.repositories.PreciousMetalService {
    @Override
    public PreciousMetalEntity getById(UUID id) {
        return getById(id);
    }
}
