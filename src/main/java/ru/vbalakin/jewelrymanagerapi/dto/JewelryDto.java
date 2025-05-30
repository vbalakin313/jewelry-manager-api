package ru.vbalakin.jewelrymanagerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JewelryDto {

    private String uin;

    @NonNull
    private String name;

    @NonNull
    private String description;

    private Double weight;

    @NonNull
    @Enumerated(EnumType.STRING)
    private MetalType material;

}