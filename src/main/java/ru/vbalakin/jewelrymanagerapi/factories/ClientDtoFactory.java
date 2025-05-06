package ru.vbalakin.jewelrymanagerapi.factories;

import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.ClientDto;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

@Component
public class ClientDtoFactory {

    public ClientDto makeClientDto(ClientEntity client){
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .gender(client.getGender())
                .country(client.getCountry())
                .build();
    }
}
