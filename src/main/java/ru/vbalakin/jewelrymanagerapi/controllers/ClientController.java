package ru.vbalakin.jewelrymanagerapi.controllers;


import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.factories.ClientDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.util.List;
import java.util.UUID;


@RestController
public class ClientController {

    private ClientRepository clientRepository;
    private CountryCodeService countryCodeService;
    private ClientDtoFactory clientDtoFactory;

    public ClientController(ClientRepository clientRepository,ClientDtoFactory clientDtoFactory,CountryCodeService countryCodeService) {
        this.clientRepository = clientRepository;
        this.clientDtoFactory = clientDtoFactory;
        this.countryCodeService = countryCodeService;
    }

    private static final String ALL_CLIENTS = "/api/v1/clients";
    private static final String CREATE_CLIENT = "/api/v1/clients";
    private static final String DELETE_CLIENT = "/api/v1/clients/{id}";

    @GetMapping(ALL_CLIENTS)
    public List<ClientEntity> allClients(){

        return clientRepository.findAll();
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
                        .build()
        );

        return clientDtoFactory.makeClientDto(client);
    }

    @DeleteMapping(DELETE_CLIENT)
    void deleteClient(@PathVariable UUID id){

         clientRepository.deleteById(id);
    }
}