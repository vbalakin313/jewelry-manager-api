package ru.vbalakin.jewelrymanagerapi.factories.historyFactories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryClientInfoDto;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryJewelryInfoDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryJewelryInfoDtoFactory {

    public List<HistoryJewelryInfoDto> makeHistoryJewelryInfoDtos(List<JewelryEntity> jewelries) {
        return jewelries.stream()
                .map(this::makeHistoryJewelryInfoDto)
                .collect(Collectors.toList());
    }

    public HistoryJewelryInfoDto makeHistoryJewelryInfoDto(JewelryEntity jewelry) {
        return HistoryJewelryInfoDto.builder()
                .id(jewelry.getId())
                .name(jewelry.getName())
                .createdAt(jewelry.getCreatedAt())
                .build();
    }
}
