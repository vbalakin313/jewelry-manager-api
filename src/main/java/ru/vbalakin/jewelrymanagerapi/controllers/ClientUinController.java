package ru.vbalakin.jewelrymanagerapi.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.dto.UinDto;
import ru.vbalakin.jewelrymanagerapi.dto.UinRequest;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.factories.UinDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.util.Optional;
import java.util.UUID;


@RestController
@AllArgsConstructor
public class ClientUinController {

    private final UinRepository uinRepository;
    private final UinDtoFactory uinDtoFactory;
    private final ClientRepository clientRepository;

    @GetMapping("/api/v1/client/uin")
    public Optional<UinEntity> getUin(@RequestParam(value = "id", required = false) UUID clientId){

        return uinRepository.findByClientId(clientId);
    }

    @PutMapping("/api/v1/client/{clientId}/uin")
    @Transactional
    public UinDto createOrUpdateUin(
            @PathVariable UUID clientId,
            @RequestBody UinRequest request) {

        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        UinEntity uin = client.getUin();
        if (uin == null) {
            uin = new UinEntity();
            uin.setClient(client);
        }

        uin.setUin(request.getUinCode());
        uin = uinRepository.save(uin);

        return uinDtoFactory.makeUinDto(uin);
    }

}
