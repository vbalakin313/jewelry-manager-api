package ru.vbalakin.jewelrymanagerapi.services;

import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;

import java.util.UUID;

public abstract class JewelryService implements ru.vbalakin.jewelrymanagerapi.repositories.JewelryService {
    @Override
    public JewelryEntity getById(UUID id) {
        return getById(id);
    }
}
