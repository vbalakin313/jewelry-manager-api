package ru.vbalakin.jewelrymanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private UUID id;

    @NonNull
    private String uin;

    @NonNull
    @Enumerated(EnumType.STRING)
    private MetalType metalType;

    @NonNull
    private Double weight;

    @NonNull
    private String assay;

    @NonNull
    private String form;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Instant createdAt = Instant.now();

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Instant updatedAt = Instant.now();
}