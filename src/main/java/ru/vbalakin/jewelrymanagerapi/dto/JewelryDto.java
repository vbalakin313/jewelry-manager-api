package ru.vbalakin.jewelrymanagerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JewelryDto {
    @NonNull
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    private double weight;

    @NonNull
    @Enumerated(EnumType.STRING)
    private MetalType material;
}