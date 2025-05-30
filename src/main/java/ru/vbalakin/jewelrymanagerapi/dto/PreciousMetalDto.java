package ru.vbalakin.jewelrymanagerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreciousMetalDto {

    private String uin;

    @Enumerated(EnumType.STRING)
    private MetalType metalType;

    private Double weight;

    private String assay;

    private String form;
}