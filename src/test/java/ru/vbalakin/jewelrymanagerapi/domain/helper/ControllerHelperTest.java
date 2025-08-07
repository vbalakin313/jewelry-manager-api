package ru.vbalakin.jewelrymanagerapi.domain.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.factories.historyFactories.HistoryOfAdditionDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientService;
import ru.vbalakin.jewelrymanagerapi.repositories.UinService;
import ru.vbalakin.jewelrymanagerapi.services.ClientUinGeneratorService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerHelperTest {

    @Mock
    ClientService clientService;

    @Mock
    UinService uinService;

    @Mock
    HistoryOfAdditionDtoFactory historyOfAdditionDtoFactory;

    @InjectMocks
    ControllerHelper controllerHelper;

    @Test
    void getClientOrThrowException_whenClientIsExist_thenClientIsReturned() {
        UUID clientUuid = UUID.randomUUID();
        UUID countryUuid = UUID.randomUUID();
        String name = "Petr";
        String surname = "Petrovich";
        Gender gender = Gender.MALE;
        String country = "Russia";

        ClientEntity client = ClientEntity.builder()
                .id(clientUuid)
                .name(name)
                .surname(surname)
                .gender(gender)
                .country(country)
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(countryUuid)
                                .countryCode(345)
                        .build()
                )
                .build();

        Mockito.when(clientService.findById(client.getId())).thenReturn(Optional.of(client));

        ClientEntity result = controllerHelper.getClientOrThrowException(client.getId());

        assertEquals(client, result, "Client is returned");
    }

    @Test
    void getClientOrThrowException_whenClientIsNotExist_thenThrowNotFoundException() {
        UUID clientUuid = UUID.randomUUID();

        Mockito.when(clientService.findById(clientUuid)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            controllerHelper.getClientOrThrowException(clientUuid);
        });

        assertEquals("Client with this id is not found", exception.getMessage());
    }

    @Test
    void getHistoryOrThrowException_whenHistoryIsExist_thenHistoryIsReturned() {
        ClientUinGeneratorService uinGeneratorService = Mockito.mock(ClientUinGeneratorService.class);
        ClientEntity client = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name("Petr")
                .surname("Petrovich")
                .gender(Gender.MALE)
                .country("Russia")
                .clientCountryCode(
                        ClientCountryCodeEntity.builder()
                                .id(UUID.randomUUID())
                                .countryCode(345)
                                .build()
                )
                .build();

        UinEntity uin = UinEntity.builder()
                .uin(uinGeneratorService.generateClientUin(client.getId()))
                .client(client)
                .jewelries(new ArrayList<>())
                .preciousMetals(new ArrayList<>())
                .build();

        HistoryOfAdditionDto result = historyOfAdditionDtoFactory.makeHistoryOfAdditionDto(uin);

        Mockito.when(uinService.findByUin(uin.getUin())).thenReturn(Optional.of(uin));
        Mockito.when(historyOfAdditionDtoFactory.makeHistoryOfAdditionDto(uin)).thenReturn(result);


        assertEquals(result, controllerHelper.getHistoryOrThrowException(uin.getUin()));
    }

    @Test
    void getHistoryOrThrowException_whenHistoryIsNotExist_thenThrowNotFoundException() {
        String uin = "8402552457377752";

        Mockito.when(uinService.findByUin(uin)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            controllerHelper.getHistoryOrThrowException(uin);
        });

        assertEquals("Uin is not found: " + uin, exception.getMessage());
    }
}