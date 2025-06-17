package ru.vbalakin.jewelrymanagerapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.JewelryDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientCountryCodeRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.JewelryRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;
import ru.vbalakin.jewelrymanagerapi.services.CountryCodeService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JewelryControllerTest {

    @Mock
    JewelryRepository jewelryRepository;

    @Mock
    JewelryDtoFactory jewelryDtoFactory;

    @Mock
    UinRepository uinRepository;

    @InjectMocks
    JewelryController jewelryController;

    @Test
    void allJewelries_returnAllJewelries() {
        UUID jewelryId_1 = UUID.randomUUID();
        UUID jewelryId_2 = UUID.randomUUID();

        List<JewelryEntity> jewelries = List.of(
          JewelryEntity.builder()
                  .id(jewelryId_1)
                  .createdAt(Instant.now())
                  .updatedAt(Instant.now())
                  .name("Jewelry")
                  .description("Jewelry")
                  .weight(32.2)
                  .material(MetalType.PALLADIUM)
                  .build(),
                JewelryEntity.builder()
                        .id(jewelryId_2)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .name("Jewelry2")
                        .description("Jewelry2")
                        .weight(35.2)
                        .material(MetalType.PLATINUM)
                        .build()
        );

        when(jewelryRepository.findAll()).thenReturn(jewelries);

        jewelryController.allJewelries();

        assertNotNull(jewelries);
    }

    @Test
    void createJewelry_whenCreatingJewelry_returnJewelry() {
        String uin = "8402552457377752";
        String name = "Jewelry";
        String description = "Jewelry";
        double weight = 32.2;
        MetalType material = MetalType.PALLADIUM;
        String country = "Russia";

        UinEntity uinEntity = UinEntity.builder()
                .id(UUID.randomUUID())
                .uin(uin)
                .client(ClientEntity.builder()
                        .id(UUID.randomUUID())
                        .name("name")
                        .surname("surname")
                        .gender(Gender.MALE)
                        .country(country)
                        .clientCountryCode(
                                ClientCountryCodeEntity.builder()
                                        .id(UUID.randomUUID())
                                        .countryCode(643)
                                        .build()
                        )
                        .build())
                .preciousMetals(new ArrayList<>())
                .jewelries(new ArrayList<>())
                .build();

            when(uinRepository.findByUin(uin)).thenReturn(Optional.of(uinEntity));

            JewelryEntity savedJewelry = JewelryEntity.builder()
                    .id(UUID.randomUUID())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .name(name)
                    .description(description)
                    .weight(weight)
                    .material(material)
                    .build();

            when(jewelryRepository.saveAndFlush(any(JewelryEntity.class))).thenReturn(savedJewelry);

            JewelryDto expectedDto = new JewelryDto();
            expectedDto.setId(savedJewelry.getId());
            expectedDto.setName(name);
            expectedDto.setDescription(description);
            expectedDto.setWeight(weight);
            expectedDto.setMaterial(material);
            expectedDto.setCreatedAt(savedJewelry.getCreatedAt());
            expectedDto.setUpdatedAt(savedJewelry.getUpdatedAt());

            when(jewelryDtoFactory.makeJewelryDto(savedJewelry)).thenReturn(expectedDto);

            JewelryDto result = jewelryController.createJewelry(uin, name, description, weight, material);

            assertNotNull(result);
            assertEquals(expectedDto.getId(), result.getId());
            assertEquals(expectedDto.getName(), result.getName());
            assertEquals(expectedDto.getDescription(), result.getDescription());
            assertEquals(expectedDto.getWeight(), result.getWeight());
            assertEquals(expectedDto.getMaterial(), result.getMaterial());
    }

    @Test
    void editJewelry_whenEditingJewelry_returnUpdatedJewelry() {
        UUID jewelryId = UUID.randomUUID();
        String originalName = "Jewelry";
        String newName = "New Jewelry";
        String description = "Jewelry";
        double weight = 32.2;
        MetalType material = MetalType.PALLADIUM;

        JewelryEntity originalJewelry = JewelryEntity.builder()
                .id(jewelryId)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .name(originalName)
                .description(description)
                .weight(weight)
                .material(material)
                .build();

        JewelryDto expectedDto = new JewelryDto(
                jewelryId,
                newName,
                description,
                weight,
                material,
                originalJewelry.getCreatedAt(),
                Instant.now()
        );

        when(jewelryRepository.findById(jewelryId)).thenReturn(Optional.of(originalJewelry));
        when(jewelryRepository.saveAndFlush(any(JewelryEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0)); //
        when(jewelryDtoFactory.makeJewelryDto(originalJewelry)).thenReturn(expectedDto);

        JewelryDto result = jewelryController.editJewelry(
          jewelryId,
          newName,
          material,
          description,
          weight
        );

        assertNotNull(result);
    }
}