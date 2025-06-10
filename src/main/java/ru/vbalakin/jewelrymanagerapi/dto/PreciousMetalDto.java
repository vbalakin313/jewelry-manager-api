package ru.vbalakin.jewelrymanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreciousMetalDto {
    @NonNull
    @Schema(description = "Unique metal identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NonNull
    @Schema(description = "Unique UIN", example = "8402552457377752", accessMode = Schema.AccessMode.READ_ONLY)
    private String uin;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Metal type")
    private MetalType metalType;

    @NonNull
    @Schema(description = "Weight of metal")
    private Double weight;

    @NonNull
    @Schema(description = "Assay")
    private String assay;

    @NonNull
    @Schema(description = "Form")
    private String form;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Schema(description = "Date metal was added to storage", example = "2025-01-01 19:52:32", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Schema(description = "Metal update date", example = "2025-01-01 19:52:32", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant updatedAt = Instant.now();
}