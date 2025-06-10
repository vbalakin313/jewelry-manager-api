package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Date the jewelry was added to storage", example = "2025-01-01 19:52:32", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();

    @NonNull
    @Schema(description = "Name of the decoration")
    private String name;

    @NonNull
    @Schema(description = "Unique jewelry identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
}
