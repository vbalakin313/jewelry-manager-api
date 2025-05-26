package ru.vbalakin.jewelrymanagerapi.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.factories.ClientDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;
    private final CountryCodeService countryCodeService;
    private final ClientDtoFactory clientDtoFactory;
    private final ControllerHelper helper;

    private static final String ALL_CLIENTS = "/api/v1/clients";
    private static final String CREATE_CLIENT = "/api/v1/clients";
    private static final String EDIT_CLIENT = "/api/v1/clients";
    private static final String DELETE_CLIENT = "/api/v1/clients/{id}";

    @GetMapping(ALL_CLIENTS)
    public List<ClientDto> allClients(){
        List<ClientEntity> clients = clientRepository.findAll();

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

       ClientEntity updatedClient = clientRepository.saveAndFlush(client);

        return clientDtoFactory.makeClientDto(updatedClient);
    }

    @PutMapping(CREATE_CLIENT)
    public ClientDto createClient(@RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam Gender gender,
                                  @RequestParam String country){

        ClientEntity client = clientRepository.saveAndFlush(
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

    @DeleteMapping(DELETE_CLIENT)
    void deleteClient(@PathVariable UUID id){

        helper.getClientOrThrowException(id);

        clientRepository.deleteById(id);
    }
}