package ru.vbalakin.jewelrymanagerapi.factories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vbalakin.jewelrymanagerapi.dto.UinDto;
import ru.vbalakin.jewelrymanagerapi.entities.UinEntity;

@Component
@AllArgsConstructor
public class UinDtoFactory {

    public UinDto makeUinDto(UinEntity uinEntity) {
        return UinDto.builder()
                .uin(uinEntity.getUin())
                .build();
    }
}
