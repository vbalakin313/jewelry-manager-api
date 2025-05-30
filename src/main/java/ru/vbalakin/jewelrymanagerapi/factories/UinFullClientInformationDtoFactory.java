package ru.vbalakin.jewelrymanagerapi.factories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.UinFullClientInformationDto;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;


@Component
@AllArgsConstructor
public class UinFullClientInformationDtoFactory {

    private final JewelryDtoFactory jewelryDtoFactory;
    private final PreciousMetalDtoFactory preciousMetalDtoFactory;
    private final ClientDtoFactory clientDtoFactory;

    public UinFullClientInformationDto makeUinFullClientInfoDto(UinEntity uinEntity) {
        return UinFullClientInformationDto.builder()
                .uin(uinEntity.getUin())
                .client(clientDtoFactory.makeClientDto(uinEntity.getClient()))
                .jewelries(jewelryDtoFactory.makeJewelryDtos(uinEntity.getJewelries()))
                .preciousMetals(preciousMetalDtoFactory.makePreciousMetalDtos(uinEntity.getPreciousMetals()))
                .build();
    }
}
