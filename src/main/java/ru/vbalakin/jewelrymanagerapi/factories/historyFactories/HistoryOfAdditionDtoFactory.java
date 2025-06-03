package ru.vbalakin.jewelrymanagerapi.factories.historyFactories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

@Component
@AllArgsConstructor
public class HistoryOfAdditionDtoFactory {

    private final HistoryClientInfoDtoFactory clientInfoDtoFactory;
    private final HistoryJewelryInfoDtoFactory jewelryInfoDtoFactory;
    private final HistoryPreciousMetalInfoDtoFactory preciousMetalInfoDtoFactory;

    public HistoryOfAdditionDto makeHistoryOfAdditionDto(UinEntity uinEntity) {

        return HistoryOfAdditionDto.builder()
                .clientInfo(clientInfoDtoFactory.makeHistoryClientInfoDto(uinEntity.getClient()))
                .jewelriesInfo(jewelryInfoDtoFactory.makeHistoryJewelryInfoDtos(uinEntity.getJewelries().reversed()))
                .preciousMetalsInfo(preciousMetalInfoDtoFactory.makeHistoryPreciousMetalInfoDtos(uinEntity.getPreciousMetals().reversed()))
                .build();
    }
}
