package ru.vbalakin.jewelrymanagerapi.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.vbalakin.jewelrymanagerapi.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
