package ru.vbalakin.jewelrymanagerapi.controllers;

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

import java.util.List;

@RestController
@Transactional
@AllArgsConstructor
public class JewelryController {

    private final JewelryRepository jewelryRepository;
    private final JewelryDtoFactory jewelryDtoFactory;

    private static final String CREATE_JEWELRY = "/api/v1/jewelries";
    private static final String ALL_JEWELRY = "/api/v1/jewelries";
    private static final String DELETE_JEWELRY = "/api/v1/jewelries/{uin}";
    private static final String EDIT_JEWELRY = "/api/v1/jewelries";
    private final UinRepository uinRepository;


    @GetMapping(ALL_JEWELRY)
    public List<JewelryDto> allJewelries(){

        List<JewelryEntity> jewelries = jewelryRepository.findAll();

        return jewelryDtoFactory.makeJewelryDtos(jewelries);
    }

    @PutMapping(CREATE_JEWELRY)
    public JewelryDto createJewelry(@RequestParam String uin,
                                    @RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam Double weight,
                                    @RequestParam MetalType material){

        UinEntity uinEntity = uinRepository.findByUin(uin).orElseThrow(
                () -> new NullPointerException("Uin not found")
        );


        JewelryEntity jewelry = jewelryRepository.saveAndFlush(
                JewelryEntity.builder()
                        .name(name)
                        .description(description)
                        .weight(weight)
                        .material(material)
                        .uin(uinEntity)
                        .build()
        );

        return jewelryDtoFactory.makeJewelryDto(jewelry);
    }

    @PatchMapping(EDIT_JEWELRY)
    public JewelryDto editJewelry(@RequestParam(value = "uin_id", required = false) String uin,
                                @RequestParam String name,
                                @RequestParam MetalType material,
                                @RequestParam String description,
                                @RequestParam Double weight){

        UinEntity uinEntity = uinRepository.findByUin(uin)
                .orElseThrow(() -> new EntityNotFoundException("UIN not found"));

        JewelryEntity jewelry = jewelryRepository.findByUin(uinEntity)
                .orElseThrow(() -> new EntityNotFoundException("Jewelry not found"));

        jewelry.setName(name);
        jewelry.setMaterial(material);
        jewelry.setDescription(description);
        jewelry.setWeight(weight);

        JewelryEntity updatedJewelry = jewelryRepository.saveAndFlush(jewelry);

        return jewelryDtoFactory.makeJewelryDto(updatedJewelry);
    }

    @DeleteMapping(DELETE_JEWELRY)
    void deleteJewelry(@PathVariable String uin){
        jewelryRepository.deleteByUin_Uin(uin);

    }
}
