package ru.vbalakin.jewelrymanagerapi.domain.helper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ControllerHelper {
    private final ClientRepository clientRepository;

    public ClientEntity getClientOrThrowException(UUID id){
         return clientRepository.findById(id).orElseThrow(
                 () -> new NotFoundException(String.format("Client with this id is not found"))
         );
    }

}
