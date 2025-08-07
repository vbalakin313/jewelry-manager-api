package ru.vbalakin.jewelrymanagerapi.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.factories.ClientDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientService;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@Transactional
@AllArgsConstructor
@Tag(name = "Clients", description = "Allows you to perform operation on users")
public class ClientController {

    private final ClientService clientService;
    private final CountryCodeService countryCodeService;
    private final ClientDtoFactory clientDtoFactory;
    private final ControllerHelper helper;

    private static final String ALL_CLIENTS = "/api/v1/clients";
    private static final String CREATE_CLIENT = "/api/v1/clients";
    private static final String EDIT_CLIENT = "/api/v1/clients";
    private static final String DELETE_CLIENT = "/api/v1/clients/{id}";

    @Operation(
            summary = "Display all client"
    )
    @GetMapping(ALL_CLIENTS)
    public List<ClientDto> allClients(){
        List<ClientEntity> clients = clientService.findAll();

        return clients.stream()
                .map(client -> {
                    ClientDto dto = new ClientDto();
                    dto.setId(client.getId());
                    dto.setName(client.getName());
                    dto.setSurname(client.getSurname());
                    dto.setGender(client.getGender());
                    dto.setCountry(client.getCountry());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Edit client by ID"
    )
    @PatchMapping(EDIT_CLIENT)
    public ClientDto editClient(@RequestParam(value = "id", required = false) UUID optionalClientId,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam Gender gender,
                                   @RequestParam String country){

        ClientEntity client = helper.getClientOrThrowException(optionalClientId);

        client.setName(name);
        client.setSurname(surname);
        client.setGender(gender);
        client.setCountry(country);
        client.setClientCountryCode(countryCodeService.getNumericCountryCode(country));

       ClientEntity updatedClient = clientService.saveAndFlush(client);

        return clientDtoFactory.makeClientDto(updatedClient);
    }

    @Operation(
            summary = "Create client"
    )
    @PutMapping(CREATE_CLIENT)
    public ClientDto createClient(@RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam Gender gender,
                                  @RequestParam String country){

        ClientEntity client = clientService.saveAndFlush(
                ClientEntity.builder()
                        .name(name)
                        .surname(surname)
                        .gender(gender)
                        .country(country)
                        .clientCountryCode(countryCodeService.getNumericCountryCode(country))
                        .build()
        );

        return clientDtoFactory.makeClientDto(client);
    }

    @Operation(
            summary = "Removing client by ID"
    )
    @DeleteMapping(DELETE_CLIENT)
    void deleteClient(@PathVariable UUID id){

        helper.getClientOrThrowException(id);

        clientService.deleteById(id);
    }
}