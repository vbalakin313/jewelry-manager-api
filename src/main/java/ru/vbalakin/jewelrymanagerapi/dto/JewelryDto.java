package ru.vbalakin.jewelrymanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JewelryDto {
    @NonNull
    @Schema(description = "Unique jewelry identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NonNull
    @Schema(description = "Unique UIN", example = "8402552457377752", accessMode = Schema.AccessMode.READ_ONLY)
    private String uin;

    @NonNull
    @Schema(description = "Name of jewelry")
    private String name;

    @NonNull
    @Schema(description = "Description of jewelry")
    private String description;

    @NonNull
    @Schema(description = "Weight of jewelry")
    private Double weight;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Schema(description = "Date the jewelry was added to storage", example = "2025-01-01 19:52:32", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Schema(description = "Jewelry update date", example = "2025-01-01 19:52:32", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant updatedAt = Instant.now();

    @NonNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Material type")
    private MetalType material;
}