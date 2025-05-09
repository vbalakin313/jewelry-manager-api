package ru.vbalakin.jewelrymanagerapi.dto;


import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UinDto {
    @NonNull
    private String uin;

    private List<JewelryDto> jewelries;

    private List<PreciousMetalDto> preciousMetals;
}
