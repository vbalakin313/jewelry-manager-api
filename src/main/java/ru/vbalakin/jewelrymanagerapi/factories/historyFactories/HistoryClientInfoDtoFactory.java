package ru.vbalakin.jewelrymanagerapi.factories.historyFactories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryClientInfoDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

@Component
public class HistoryClientInfoDtoFactory {

    public HistoryClientInfoDto makeHistoryClientInfoDto(ClientEntity client){
        return HistoryClientInfoDto.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .build();
    }
}
