package ru.vbalakin.jewelrymanagerapi.factories.historyFactories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryJewelryInfoDto;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryPreciousMetalInfoDto;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryPreciousMetalInfoDtoFactory {

    public List<HistoryPreciousMetalInfoDto> makeHistoryPreciousMetalInfoDtos(List<PreciousMetalEntity> preciousMetal) {
        return preciousMetal.stream()
                .map(this::makeHistoryPreciousMetalInfoDto)
                .collect(Collectors.toList());
    }

    public HistoryPreciousMetalInfoDto makeHistoryPreciousMetalInfoDto(PreciousMetalEntity preciousMetal) {
        return HistoryPreciousMetalInfoDto.builder()
                .metalType(preciousMetal.getMetalType())
                .id(preciousMetal.getId())
                .createdAt(preciousMetal.getCreatedAt())
                .build();
    }
}
