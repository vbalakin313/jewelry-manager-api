package ru.vbalakin.jewelrymanagerapi.domain.helper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;
import ru.vbalakin.jewelrymanagerapi.factories.historyFactories.HistoryOfAdditionDtoFactory;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;
import ru.vbalakin.jewelrymanagerapi.repositories.UinRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ControllerHelper {
    private final ClientRepository clientRepository;
    private final UinRepository uinRepository;
    private final HistoryOfAdditionDtoFactory historyOfAdditionDtoFactory;

    public ClientEntity getClientOrThrowException(UUID id){
         return clientRepository.findById(id).orElseThrow(
                 () -> new NotFoundException(String.format("Client with this id is not found"))
         );
    }

    public HistoryOfAdditionDto getHistoryOrThrowException(String uin) {
        UinEntity uinEntity = uinRepository.findByUin(uin)
                .orElseThrow(() -> new NotFoundException("Uin is not found: " + uin));
        return historyOfAdditionDtoFactory.makeHistoryOfAdditionDto(uinEntity);
    }
}
