package ru.vbalakin.jewelrymanagerapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.factories.ClientDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientCountryCodeRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientService;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;

    @Mock
    ClientDtoFactory clientDtoFactory;

    @Mock
    ClientCountryCodeRepository clientCountryCodeRepository;

    @Mock
    CountryCodeService countryCodeService;

    @InjectMocks
    ClientController clientController;

    @Test
    void getAllClients_returnsAllClients() {
        UUID clientId1 = UUID.randomUUID();
        UUID clientId2 = UUID.randomUUID();
        UUID countryCodeId1 = UUID.randomUUID();
        UUID countryCodeId2 = UUID.randomUUID();

        List<ClientEntity> clients = List.of(
                ClientEntity.builder()
                        .id(clientId1)
                        .name("Petr")
                        .surname("Petrovich")
                        .gender(Gender.MALE)
                        .country("Russia")
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(countryCodeId1)
                                        .countryCode(240)
                                        .build()
                        )
                        .build(),
                ClientEntity.builder()
                        .id(clientId2)
                        .name("Ivan")
                        .surname("Ivanovich")
                        .gender(Gender.MALE)
                        .country("Russia")
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(countryCodeId2)
                                        .countryCode(240)
                                        .build()
                        )
                        .build()
        );

        Mockito.when(clientService.findAll()).thenReturn(clients);

        var result = clientController.allClients();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void createClient_returnsClient() {
        String name = "Petr";
        String surname = "Petrovich";
        Gender gender = Gender.MALE;
        String country = "Russia";

        ClientCountryCodeEntity countryCode = new ClientCountryCodeEntity();
        Mockito.when(countryCodeService.getNumericCountryCode(country))
                .thenReturn(countryCode);

        ClientEntity savedClient = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .gender(gender)
                .country(country)
                .clientCountryCode(countryCode)
                .build();

        Mockito.when(clientService.saveAndFlush(Mockito.any(ClientEntity.class)))
                .thenReturn(savedClient);

        ClientDto expectedDto = new ClientDto();
        expectedDto.setName(name);
        expectedDto.setSurname(surname);
        expectedDto.setGender(gender);
        expectedDto.setCountry(country);

        Mockito.when(clientDtoFactory.makeClientDto(savedClient)).thenReturn(expectedDto);

        ClientDto result = clientController.createClient(name, surname, gender, country);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
        assertEquals(gender, result.getGender());
        assertEquals(country, result.getCountry());
    }

    @Test
    void updateClient_returnsClient() {
        UUID clientId1 = UUID.randomUUID();
        UUID countryCodeId1 = UUID.randomUUID();

        ClientEntity originalClient = ClientEntity.builder()
                .id(clientId1)
                .name("Petr")
                .surname("Petrovich")
                .gender(Gender.MALE)
                .country("Russia")
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(countryCodeId1)
                                .countryCode(240)
                                .build()
                )
                .build();

        ClientEntity updatedClient = ClientEntity.builder()
                .id(clientId1)
                .name("Alexandr")
                .surname("Alexandrov")
                .gender(Gender.MALE)
                .country("Belarus")
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(countryCodeId1)
                                .countryCode(569)
                                .build()
                )
                .build();

        Mockito.when(clientService.saveAndFlush(Mockito.any(ClientEntity.class)))
                .thenReturn(updatedClient);
        Mockito.when(clientDtoFactory.makeClientDto(updatedClient))
                .thenReturn(new ClientDto(updatedClient.getId(),
                        updatedClient.getName(),
                        updatedClient.getSurname(),
                        updatedClient.getGender(),
                        updatedClient.getCountry())
                );

        ClientEntity result = clientService.saveAndFlush(updatedClient);
        ClientDto dtoResult = clientDtoFactory.makeClientDto(result);

        assertEquals(clientId1, dtoResult.getId());
        assertEquals("Alexandr", dtoResult.getName());
        assertEquals("Alexandrov", dtoResult.getSurname());
        assertEquals(Gender.MALE, dtoResult.getGender());
        assertEquals("Belarus", dtoResult.getCountry());
    }
}