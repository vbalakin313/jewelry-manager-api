package ru.vbalakin.jewelrymanagerapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.UinDto;
import ru.vbalakin.jewelrymanagerapi.dto.UinFullClientInformationDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.UinDtoFactory;
import ru.vbalakin.jewelrymanagerapi.factories.UinFullClientInformationDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.UinService;
import ru.vbalakin.jewelrymanagerapi.services.ClientUinGeneratorService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientUinControllerTest {

    @Mock
    UinService uinService;

    @Mock
    UinDtoFactory uinDtoFactory;

    @Mock
    UinFullClientInformationDtoFactory uinFullClientInformationDtoFactory;

    @Mock
    ClientUinGeneratorService clientUinGeneratorService;

    @Mock
    ControllerHelper helper;

    @InjectMocks
    ClientUinController clientUinController;

    @Test
    void getUin_whenUinIsExist_returnUin() {
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();
        String uin = "8402552457377752";

        UinEntity uinEntity = UinEntity.builder()
                .id(UUID.randomUUID())
                .uin(uin)
                .client(ClientEntity.builder()
                        .id(clientId)
                        .name("Petr")
                        .surname("Petrovich")
                        .gender(Gender.MALE)
                        .country("Russia")
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(clientCountryId)
                                        .countryCode(240)
                                        .build()
                        )
                        .build())
                .preciousMetals(new ArrayList<>())
                .jewelries(new ArrayList<>())
                .build();

        ClientEntity clientEntity = ClientEntity.builder()
                .id(clientId)
                .name("Petr")
                .surname("Petrovich")
                .gender(Gender.MALE)
                .country("Russia")
                .uin(uinEntity)
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(clientCountryId)
                                .countryCode(240)
                                .build()
                )
                .build();

        Mockito.when(helper.getClientOrThrowException(Mockito.any())).thenReturn(clientEntity);
        Mockito.when(uinService.findByClientId(clientEntity.getId())).thenReturn(Optional.of(uinEntity));
        Mockito.when(uinDtoFactory.makeUinDto(uinEntity)).thenReturn(new UinDto());

        Optional<UinDto> uinDto = clientUinController.getUin(clientId);

        assertNotNull(uinDto);
        assertTrue(uinDto.isPresent());
    }

    @Test
    void createUin_whenClientIsExist_returnUin() {
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();
        String uin = "8402552457377752";;

        ClientEntity clientEntity = ClientEntity.builder()
                .id(clientId)
                .name("Petr")
                .surname("Petrovich")
                .gender(Gender.MALE)
                .country("Russia")
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(clientCountryId)
                                .countryCode(240)
                                .build()
                )
                .build();

        Mockito.when(helper.getClientOrThrowException(Mockito.any())).thenReturn(clientEntity);

        UinEntity uinEntity = UinEntity.builder()
                .id(UUID.randomUUID())
                .uin(uin)
                .client(ClientEntity.builder()
                        .id(clientId)
                        .name("Petr")
                        .surname("Petrovich")
                        .gender(Gender.MALE)
                        .country("Russia")
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(clientCountryId)
                                        .countryCode(240)
                                        .build()
                        )
                        .build())
                .preciousMetals(new ArrayList<>())
                .jewelries(new ArrayList<>())
                .build();

        Mockito.when(uinService.saveAndFlush(Mockito.any())).thenReturn(uinEntity);

        clientEntity.setUin(uinEntity);

        Mockito.when(uinDtoFactory.makeUinDto(uinEntity)).thenReturn(new UinDto());

        UinDto uinDto = clientUinController.createUin(clientId);

        assertNotNull(uinDto);
    }

    @Test
    void getFullInformation_whenUinIsExist_returnFullInformation() {
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();
        String uin = "8402552457377752";;

        UinEntity uinEntity = UinEntity.builder()
                .id(UUID.randomUUID())
                .uin(uin)
                .client(ClientEntity.builder()
                        .id(clientId)
                        .name("Petr")
                        .surname("Petrovich")
                        .gender(Gender.MALE)
                        .country("Russia")
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(clientCountryId)
                                        .countryCode(240)
                                        .build()
                        )
                        .build())
                .preciousMetals(new ArrayList<>())
                .jewelries(new ArrayList<>())
                .build();

        Mockito.when(uinService.findByUin(Mockito.any())).thenReturn(Optional.ofNullable(uinEntity));

        Mockito.when(uinFullClientInformationDtoFactory.makeUinFullClientInfoDto(Mockito.any(UinEntity.class)))
                .thenReturn(new UinFullClientInformationDto());

        UinFullClientInformationDto result = clientUinController.getFullInformation(uin);

        assertNotNull(result);
    }
}