package ru.vbalakin.jewelrymanagerapi.controllers;


import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;
import ru.vbalakin.jewelrymanagerapi.factories.ClientDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ClientController {

    private final ClientRepository clientRepository;
    private final CountryCodeService countryCodeService;
    private final ClientDtoFactory clientDtoFactory;
    private final ControllerHelper helper;

    public ClientController(ClientRepository clientRepository,
                            ClientDtoFactory clientDtoFactory,
                            CountryCodeService countryCodeService,
                            ControllerHelper helper) {
        this.clientRepository = clientRepository;
        this.clientDtoFactory = clientDtoFactory;
        this.countryCodeService = countryCodeService;
        this.helper = helper;
    }

    private static final String ALL_CLIENTS = "/api/v1/clients";
    private static final String CREATE_CLIENT = "/api/v1/clients";
    private static final String EDIT_CLIENT = "/api/v1/clients";
    private static final String DELETE_CLIENT = "/api/v1/clients/{id}";

    @GetMapping(ALL_CLIENTS)
    public List<ClientEntity> allClients(){

        return clientRepository.findAll();
    }

    @PatchMapping(EDIT_CLIENT)
    public ClientEntity editClient(@RequestParam(value = "id", required = false) UUID optionalClientId,
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

        return updatedClient;
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

         clientRepository.deleteById(id);
    }
}