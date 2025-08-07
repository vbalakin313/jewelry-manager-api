package ru.vbalakin.jewelrymanagerapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientUinGeneratorServiceTest {

    @Mock
    CountryCodeService countryCodeService;

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientUinGeneratorService service;

    @Test
    void ClientUinGeneratorServiceTest_generateUniqueUins() {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCountry("Country");

        when(clientService.findById(any(UUID.class))).thenReturn(Optional.of(clientEntity));

        ClientCountryCodeEntity clientCountryCodeEntity = new ClientCountryCodeEntity();
        clientCountryCodeEntity.setCountryCode(123);

        when(countryCodeService.getNumericCountryCode(any(String.class))).thenReturn(clientCountryCodeEntity);

        when(clientService.existsByUin_Uin(any(String.class))).thenReturn(false);

        Set<String> uins = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            UUID clientId = UUID.randomUUID();
            try {
                String uin = service.generateClientUin(clientId);
                assertFalse(uins.contains(uin), "The generated uin was not unique" + uin);
                uins.add(uin);
            } catch (Exception e) {
                System.out.println("Error generating UIN: " + e.getMessage());
                throw e;
            }
        }
    }
}