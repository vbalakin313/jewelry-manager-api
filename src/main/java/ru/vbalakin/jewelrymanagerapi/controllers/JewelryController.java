package ru.vbalakin.jewelrymanagerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.factories.JewelryDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.JewelryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class JewelryController {

    private final JewelryRepository jewelryRepository;
    private final JewelryDtoFactory jewelryDtoFactory;

    private static final String CREATE_JEWELRY = "/api/v1/jewelries";
    private static final String ALL_JEWELRY = "/api/v1/jewelries";
    private static final String DELETE_JEWELRY = "/api/v1/jewelries/{id}";
    private static final String EDIT_JEWELRY = "/api/v1/jewelries";


    @GetMapping(ALL_JEWELRY)
    public List<JewelryDto> allJewelries(){

        List<JewelryEntity> jewelries = jewelryRepository.findAll();

        return jewelries.stream().map(
                jewelry -> {
                    JewelryDto jewelryDto = new JewelryDto();
                    jewelryDto.setId(jewelry.getId());
                    jewelryDto.setName(jewelry.getName());
                    jewelryDto.setDescription(jewelry.getDescription());
                    jewelryDto.setWeight(jewelry.getWeight());
                    jewelryDto.setMaterial(jewelry.getMaterial());
                    return jewelryDto;
                }).collect(Collectors.toList());
    }

    @PutMapping(CREATE_JEWELRY)
    public JewelryDto createJewelry(@RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam double weight,
                                    @RequestParam MetalType material){

        JewelryEntity jewelry = jewelryRepository.saveAndFlush(
                JewelryEntity.builder()
                        .name(name)
                        .description(description)
                        .weight(weight)
                        .material(material)
                        .build()
        );

        return jewelryDtoFactory.makeJewelryDto(jewelry);
    }

    @PatchMapping(EDIT_JEWELRY)
    public JewelryDto editJewelry(@RequestParam(value = "id", required = false) UUID optionalJewelryId,
                                @RequestParam String name,
                                @RequestParam MetalType material,
                                @RequestParam String description,
                                @RequestParam double weight){

        JewelryEntity jewelry = jewelryRepository.getById(optionalJewelryId);

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
