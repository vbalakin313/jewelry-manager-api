package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOfAdditionDto {

    @NonNull
    private HistoryClientInfoDto clientInfo;

    @NonNull
    private List<HistoryJewelryInfoDto> jewelriesInfo;

    @NonNull
    private List<HistoryPreciousMetalInfoDto> preciousMetalsInfo;
}
