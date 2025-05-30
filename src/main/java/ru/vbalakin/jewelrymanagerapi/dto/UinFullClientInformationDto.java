package ru.vbalakin.jewelrymanagerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UinFullClientInformationDto {
    private String uin;

    private ClientDto client;

    private List<JewelryDto> jewelries;

    private List<PreciousMetalDto> preciousMetals;
}
