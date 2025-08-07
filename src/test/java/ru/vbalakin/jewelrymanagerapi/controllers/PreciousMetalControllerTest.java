package ru.vbalakin.jewelrymanagerapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.PreciousMetalDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.PreciousMetalDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.PreciousMetalService;
import ru.vbalakin.jewelrymanagerapi.repositories.UinService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PreciousMetalControllerTest {

    @Mock
    PreciousMetalService preciousMetalService;

    @Mock
    PreciousMetalDtoFactory preciousMetalDtoFactory;

    @Mock
    UinService uinService;

    @InjectMocks
    PreciousMetalController preciousMetalController;

    @Test
    void allMetals_returnAllMetals() {
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();

        UinEntity uinEntity = UinEntity.builder()
                .id(UUID.randomUUID())
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

        List<PreciousMetalEntity> preciousMetalEntities = new ArrayList<>(
                List.of(
                        PreciousMetalEntity.builder()
                                .metalType(MetalType.GOLD)
                                .weight(34.2)
                                .assay("342")
                                .form("cube")
                                .uin(uinEntity)
                                .createdAt(createdAt)
                                .updatedAt(updatedAt)
                                .build(),
                        PreciousMetalEntity.builder()
                                .metalType(MetalType.PALLADIUM)
                                .weight(39.6)
                                .assay("352")
                                .form("cube")
                                .uin(uinEntity)
                                .createdAt(createdAt)
                                .updatedAt(updatedAt)
                                .build()
                )
        );

        Mockito.when(preciousMetalService.findAll()).thenReturn(preciousMetalEntities);

        preciousMetalController.allMetals();

        assertNotNull(preciousMetalEntities);

    }

    @Test
    void createMetal_whenCreatingMetal_returnMetal() {
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();
        String uin = "8402552457377752";
        MetalType metalType = MetalType.GOLD;
        Double weight = 34.2;
        String assay = "B342";
        String form = "cube";

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

        Mockito.when(uinService.findByUin(Mockito.any())).thenReturn(Optional.of(uinEntity));

        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();

        PreciousMetalEntity metalEntity = PreciousMetalEntity.builder()
                    .metalType(metalType)
                    .weight(weight)
                    .assay(assay)
                    .form(form)
                    .uin(uinEntity)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();

        Mockito.when(preciousMetalService.saveAndFlush(Mockito.any())).thenReturn(metalEntity);

        PreciousMetalDto preciousMetalDto = new PreciousMetalDto();
        preciousMetalDto.setMetalType(MetalType.PALLADIUM);
        preciousMetalDto.setWeight(weight);
        preciousMetalDto.setAssay(assay);
        preciousMetalDto.setForm(form);
        preciousMetalDto.setUin(uin);
        preciousMetalDto.setCreatedAt(updatedAt);
        preciousMetalDto.setUpdatedAt(updatedAt);

        Mockito.when(preciousMetalDtoFactory.makePreciousMetalDto(metalEntity)).thenReturn(preciousMetalDto);

        PreciousMetalDto result = preciousMetalController.createMetal(uin,metalType,weight,assay,form);

        assertNotNull(result);
    }

    @Test
    void editMetal_whenEditingMetal_returnMetal() {
        UUID id = UUID.randomUUID();
        MetalType metalType = MetalType.GOLD;
        Double weight = 34.2;
        String assay = "B342";
        String form = "cube";
        UUID clientId = UUID.randomUUID();
        UUID clientCountryId = UUID.randomUUID();
        String uin = "8402552457377752";
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();

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

        PreciousMetalEntity metalEntity = PreciousMetalEntity.builder()
                .id(id)
                .metalType(metalType)
                .weight(weight)
                .assay(assay)
                .form(form)
                .uin(uinEntity)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        Mockito.when(preciousMetalService.getById(Mockito.any())).thenReturn(metalEntity);

        Instant newUpdatedAt = Instant.now();
        metalEntity.setMetalType(metalType);
        metalEntity.setWeight(weight);
        metalEntity.setAssay(assay);
        metalEntity.setForm(form);
        metalEntity.setUpdatedAt(newUpdatedAt);

        Mockito.when(preciousMetalService.saveAndFlush(Mockito.any())).thenReturn(metalEntity);

        PreciousMetalDto preciousMetalDto = new PreciousMetalDto();
        preciousMetalDto.setMetalType(metalType);
        preciousMetalDto.setWeight(weight);
        preciousMetalDto.setAssay(assay);
        preciousMetalDto.setForm(form);
        preciousMetalDto.setUin(uin);
        preciousMetalDto.setCreatedAt(createdAt);
        preciousMetalDto.setUpdatedAt(newUpdatedAt);

        Mockito.when(preciousMetalDtoFactory.makePreciousMetalDto(metalEntity)).thenReturn(preciousMetalDto);

        PreciousMetalDto result = preciousMetalController.editMetal(id, metalType,weight,assay,form);

        assertNotNull(result);
        assertEquals(metalType, result.getMetalType());
        assertEquals(weight, result.getWeight());
        assertEquals(assay, result.getAssay());
        assertEquals(form, result.getForm());
        assertEquals(uin, result.getUin());
        assertEquals(newUpdatedAt, result.getUpdatedAt());
    }
}