package ru.vbalakin.jewelrymanagerapi.controllers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.PreciousMetalDto;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;
import ru.vbalakin.jewelrymanagerapi.factories.PreciousMetalDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.PreciousMetalRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.util.List;

@RestController
@Transactional
@AllArgsConstructor
public class PreciousMetalController {

    private final PreciousMetalRepository preciousMetalRepository;
    private final PreciousMetalDtoFactory preciousMetalDtoFactory;

    private static final String CREATE_METAL = "/api/v1/metals";
    private static final String ALL_METAL = "/api/v1/metals";
    private static final String EDIT_METAL = "/api/v1/metals";
    private static final String DELETE_METAL = "/api/v1/metals/{uin}";
    private final UinRepository uinRepository;

    @PutMapping(CREATE_METAL)
    public PreciousMetalDto createMetal(@RequestParam String uin,
                                        @RequestParam MetalType metalType,
                                        @RequestParam Double weight,
                                        @RequestParam String assay,
                                        @RequestParam String form){


        UinEntity uinEntity = uinRepository.findByUin(uin).orElseThrow(
                () -> new BadRequestException("Uin not found")
        );

        PreciousMetalEntity preciousMetal = preciousMetalRepository.saveAndFlush(
                PreciousMetalEntity.builder()
                        .metalType(metalType)
                        .weight(weight)
                        .assay(assay)
                        .form(form)
                        .uin(uinEntity)
                        .build()
        );

        return preciousMetalDtoFactory.makePreciousMetalDto(preciousMetal);
    }

    @GetMapping(ALL_METAL)
    public List<PreciousMetalDto> allMetals(){

        List<PreciousMetalEntity> preciousMetal = preciousMetalRepository.findAll();

        return preciousMetalDtoFactory.makePreciousMetalDtos(preciousMetal);
    }

    @PatchMapping(EDIT_METAL)
    public PreciousMetalDto editMetal(@RequestParam(value = "uin_id", required = false) String uin,
                                      @RequestParam MetalType metalType,
                                      @RequestParam Double weight,
                                      @RequestParam String assay,
                                      @RequestParam String form){

          PreciousMetalEntity preciousMetal = preciousMetalRepository.getByUin_Uin(uin);

          preciousMetal.setMetalType(metalType);
          preciousMetal.setWeight(weight);
          preciousMetal.setAssay(assay);
          preciousMetal.setForm(form);

          PreciousMetalEntity updatedPreciousMetal = preciousMetalRepository.saveAndFlush(preciousMetal);

          return preciousMetalDtoFactory.makePreciousMetalDto(updatedPreciousMetal);
    }

    @DeleteMapping(DELETE_METAL)
    void deleteMetal(@PathVariable String uin){

        preciousMetalRepository.deleteByUin_Uin(uin);
    }

}
