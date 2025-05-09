package ru.vbalakin.jewelrymanagerapi.factories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.PreciousMetalDto;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreciousMetalDtoFactory {

    public List<PreciousMetalDto> makePreciousMetalDtos(List<PreciousMetalEntity> metals) {
        return metals.stream()
                .map(this::makePreciousMetalDto)
                .collect(Collectors.toList());
    }

    public PreciousMetalDto makePreciousMetalDto(PreciousMetalEntity metal) {
        return PreciousMetalDto.builder()
                .id(metal.getId())
                .metalType(metal.getMetalType())
                .weight(metal.getWeight())
                .assay(metal.getAssay())
                .form(metal.getForm())
                .build();
    }
}
