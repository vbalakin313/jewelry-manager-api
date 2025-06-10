package ru.vbalakin.jewelrymanagerapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.JewelryDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.JewelryRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@Transactional
@AllArgsConstructor
@Tag(name = "Jewelry", description = "Allows you to perform operations on jewelry")
public class JewelryController {

    private final JewelryRepository jewelryRepository;
    private final JewelryDtoFactory jewelryDtoFactory;

    private static final String CREATE_JEWELRY = "/api/v1/jewelries";
    private static final String ALL_JEWELRY = "/api/v1/jewelries";
    private static final String DELETE_JEWELRY = "/api/v1/jewelries/{id}";
    private static final String EDIT_JEWELRY = "/api/v1/jewelries";
    private final UinRepository uinRepository;


    @Operation(
            summary = "Display all decorations"
    )
    @GetMapping(ALL_JEWELRY)
    public List<JewelryDto> allJewelries(){

        List<JewelryEntity> jewelries = jewelryRepository.findAll();

        return jewelryDtoFactory.makeJewelryDtos(jewelries);
    }

    @Operation(
            summary = "Creating a decoration with user binding"
    )
    @PutMapping(CREATE_JEWELRY)
    public JewelryDto createJewelry(@RequestParam String uin,
                                    @RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam Double weight,
                                    @RequestParam MetalType material) {

        UinEntity uinEntity = uinRepository.findByUin(uin).orElseThrow(
                () -> new NullPointerException("Uin not found")
        );

        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();

        JewelryEntity jewelry = jewelryRepository.saveAndFlush(
                JewelryEntity.builder()
                        .name(name)
                        .description(description)
                        .weight(weight)
                        .material(material)
                        .createdAt(createdAt)
                        .updatedAt(updatedAt)
                        .uin(uinEntity)
                        .build()
        );

        return jewelryDtoFactory.makeJewelryDto(jewelry);
    }

    @Operation(
            summary = "Editing decoration by ID"
    )
    @PatchMapping(EDIT_JEWELRY)
    public JewelryDto editJewelry(@RequestParam(value = "id", required = false) UUID id,
                                @RequestParam String name,
                                @RequestParam MetalType material,
                                @RequestParam String description,
                                @RequestParam Double weight) {


        JewelryEntity jewelry = jewelryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID not found"));

        Instant updatedAt = Instant.now();
        jewelry.setName(name);
        jewelry.setMaterial(material);
        jewelry.setDescription(description);
        jewelry.setWeight(weight);
        jewelry.setUpdatedAt(updatedAt);

        JewelryEntity updatedJewelry = jewelryRepository.saveAndFlush(jewelry);

        return jewelryDtoFactory.makeJewelryDto(updatedJewelry);
    }

    @Operation(
            summary = "Removing jewelry by ID"
    )
    @DeleteMapping(DELETE_JEWELRY)
    void deleteJewelry(@PathVariable UUID id){
        jewelryRepository.deleteById(id);

    }
}
