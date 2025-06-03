package ru.vbalakin.jewelrymanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private UUID id;

    @NonNull
    private String uin;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double weight;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Instant createdAt = Instant.now();

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Instant updatedAt = Instant.now();

    @NonNull
    @Enumerated(EnumType.STRING)
    private MetalType material;
}