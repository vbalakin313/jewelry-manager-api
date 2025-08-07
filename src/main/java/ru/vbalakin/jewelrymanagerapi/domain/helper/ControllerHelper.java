package ru.vbalakin.jewelrymanagerapi.domain.helper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.factories.historyFactories.HistoryOfAdditionDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientService;
import ru.vbalakin.jewelrymanagerapi.repositories.UinService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ControllerHelper {
    private final ClientService clientService;
    private final UinService uinService;
    private final HistoryOfAdditionDtoFactory historyOfAdditionDtoFactory;

    public ClientEntity getClientOrThrowException(UUID id){
         return clientService.findById(id).orElseThrow(
                 () -> new NotFoundException(String.format("Client with this id is not found"))
         );
    }

    public HistoryOfAdditionDto getHistoryOrThrowException(String uin) {
        UinEntity uinEntity = uinService.findByUin(uin)
                .orElseThrow(() -> new NotFoundException("Uin is not found: " + uin));
        return historyOfAdditionDtoFactory.makeHistoryOfAdditionDto(uinEntity);
    }
}
