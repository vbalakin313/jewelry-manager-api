package ru.vbalakin.jewelrymanagerapi.factories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.JewelryDto;
import ru.vbalakin.jewelrymanagerapi.dto.PreciousMetalDto;
import ru.vbalakin.jewelrymanagerapi.dto.UinDto;
import ru.vbalakin.jewelrymanagerapi.entities.JewelryEntity;
import ru.vbalakin.jewelrymanagerapi.entities.PreciousMetalEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UinDtoFactory {

    private final JewelryDtoFactory jewelryDtoFactory;
    private final PreciousMetalDtoFactory preciousMetalDtoFactory;

    public UinDto makeUinDto(UinEntity uinEntity) {
        return UinDto.builder()
                .uin(uinEntity.getUin())
                .jewelries(jewelryDtoFactory.makeJewelryDtos(uinEntity.getJewelries()))
                .preciousMetals(preciousMetalDtoFactory.makePreciousMetalDtos(uinEntity.getPreciousMetals()))
                .build();
    }
}
