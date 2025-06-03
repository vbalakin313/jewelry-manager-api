package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryJewelryInfoDto {
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Instant createdAt = Instant.now();

    @NonNull
    private String name;

    @NonNull
    private UUID id;
}
