package ru.vbalakin.jewelrymanagerapi.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.UinDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.factories.UinDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;
import ru.vbalakin.jewelrymanagerapi.services.ClientUinGeneratorService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@RestController
@AllArgsConstructor
public class ClientUinController {

    private final UinRepository uinRepository;
    private final UinDtoFactory uinDtoFactory;
    private final ClientUinGeneratorService clientUinGeneratorService;
    private final ControllerHelper helper;

    private static final String GET_UIN = "/api/v1/client/uin";
    private static final String CREATE_UIN = "/api/v1/client/{clientId}/uin";

    @GetMapping(GET_UIN)
    public Optional<UinDto> getUin(@RequestParam(value = "id", required = false) UUID clientId){

        ClientEntity client = helper.getClientOrThrowException(clientId);

        return uinRepository.findByClientId(client.getId()).map(uinDtoFactory::makeUinDto);
    }

    @PutMapping(CREATE_UIN)
    @Transactional
    public UinDto createUin(@PathVariable UUID clientId) {

        ClientEntity client = helper.getClientOrThrowException(clientId);

        UinEntity uin = UinEntity.builder()
                .uin(clientUinGeneratorService.clientUinGenerated(client.getId()))
                .client(client)
                .jewelries(new ArrayList<>())
                .preciousMetals(new ArrayList<>())
                .build();

        if (client.getUin() != null){
            uin.setJewelries(new ArrayList<>(client.getUin().getJewelries()));
            uin.setPreciousMetals(new ArrayList<>(client.getUin().getPreciousMetals()));
        }

        uin = uinRepository.saveAndFlush(uin);

        client.setUin(uin);

        return uinDtoFactory.makeUinDto(uin);
    }

}
