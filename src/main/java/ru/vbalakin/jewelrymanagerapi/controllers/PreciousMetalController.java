package ru.vbalakin.jewelrymanagerapi.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;
import ru.vbalakin.jewelrymanagerapi.dto.PreciousMetalDto;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.factories.PreciousMetalDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.PreciousMetalRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PreciousMetalController {

    private final PreciousMetalRepository preciousMetalRepository;
    private final PreciousMetalDtoFactory preciousMetalDtoFactory;

    private static final String CREATE_METAL = "/api/v1/metals";
    private static final String ALL_METAL = "/api/v1/metals";
    private static final String EDIT_METAL = "/api/v1/metals";
    private static final String DELETE_METAL = "/api/v1/metals/{id}";
    private final UinRepository uinRepository;

    @PutMapping(CREATE_METAL)
    public PreciousMetalDto createMetal(@RequestParam UUID uinId,
                                        @RequestParam MetalType metalType,
                                        @RequestParam double weight,
                                        @RequestParam String assay,
                                        @RequestParam String form){

        UinEntity uin = uinRepository.findById(uinId).orElseThrow(
                () -> new EntityNotFoundException("UIN not found with id: " + uinId)
        );

        PreciousMetalEntity preciousMetal = preciousMetalRepository.saveAndFlush(
                PreciousMetalEntity.builder()
                        .metalType(metalType)
                        .weight(weight)
                        .assay(assay)
                        .form(form)
                        .uin(uin)
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
    public PreciousMetalDto editMetal(@RequestParam(value = "id", required = false) UUID metalId,
                                      @RequestParam MetalType metalType,
                                      @RequestParam double weight,
                                      @RequestParam String assay,
                                      @RequestParam String form){

          PreciousMetalEntity preciousMetal = preciousMetalRepository.getById(metalId);

          preciousMetal.setMetalType(metalType);
          preciousMetal.setWeight(weight);
          preciousMetal.setAssay(assay);
          preciousMetal.setForm(form);

          PreciousMetalEntity updatedPreciousMetal = preciousMetalRepository.saveAndFlush(preciousMetal);

          return preciousMetalDtoFactory.makePreciousMetalDto(updatedPreciousMetal);
    }

    @DeleteMapping(DELETE_METAL)
    void deleteMetal(@PathVariable UUID id){

        preciousMetalRepository.deleteById(id);
    }

}
