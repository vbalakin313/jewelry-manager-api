package ru.vbalakin.jewelrymanagerapi.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.JewelryDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.JewelryRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class JewelryController {

    private final JewelryRepository jewelryRepository;
    private final JewelryDtoFactory jewelryDtoFactory;

    private static final String CREATE_JEWELRY = "/api/v1/jewelries";
    private static final String ALL_JEWELRY = "/api/v1/jewelries";
    private static final String DELETE_JEWELRY = "/api/v1/jewelries/{id}";
    private static final String EDIT_JEWELRY = "/api/v1/jewelries";
    private final UinRepository uinRepository;


    @GetMapping(ALL_JEWELRY)
    public List<JewelryDto> allJewelries(){

        List<JewelryEntity> jewelries = jewelryRepository.findAll();

        return jewelryDtoFactory.makeJewelryDtos(jewelries);
    }

    @PutMapping(CREATE_JEWELRY)
    public JewelryDto createJewelry(@RequestParam UUID uinId,
                                    @RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam double weight,
                                    @RequestParam MetalType material){

        UinEntity uin = uinRepository.findById(uinId).orElseThrow(
                ()-> new EntityNotFoundException("UIN not found with id: " + uinId)
        );


        JewelryEntity jewelry = jewelryRepository.saveAndFlush(
                JewelryEntity.builder()
                        .name(name)
                        .description(description)
                        .weight(weight)
                        .material(material)
                        .uin(uin)
                        .build()
        );

        return jewelryDtoFactory.makeJewelryDto(jewelry);
    }

    @PatchMapping(EDIT_JEWELRY)
    public JewelryDto editJewelry(@RequestParam(value = "id", required = false) UUID jewelryId,
                                @RequestParam String name,
                                @RequestParam MetalType material,
                                @RequestParam String description,
                                @RequestParam double weight){

        JewelryEntity jewelry = jewelryRepository.getById(jewelryId);

        jewelry.setName(name);
        jewelry.setMaterial(material);
        jewelry.setDescription(description);
        jewelry.setWeight(weight);

        JewelryEntity updatedJewelry = jewelryRepository.saveAndFlush(jewelry);

        return jewelryDtoFactory.makeJewelryDto(updatedJewelry);
    }

    @DeleteMapping(DELETE_JEWELRY)
    void deleteJewelry(@PathVariable UUID id){

        jewelryRepository.deleteById(id);
    }
}
