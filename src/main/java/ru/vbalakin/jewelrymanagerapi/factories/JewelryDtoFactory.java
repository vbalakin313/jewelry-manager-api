package ru.vbalakin.jewelrymanagerapi.factories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JewelryDtoFactory {

    public List<JewelryDto> makeJewelryDtos(List<JewelryEntity> jewelries) {
        return jewelries.stream()
                .map(this::makeJewelryDto)
                .collect(Collectors.toList());
    }

    public JewelryDto makeJewelryDto(JewelryEntity jewelry) {
        return JewelryDto.builder()
                .uin(jewelry.getUin().getUin())
                .name(jewelry.getName())
                .description(jewelry.getDescription())
                .weight(jewelry.getWeight())
                .material(jewelry.getMaterial())
                .build();
    }
}
