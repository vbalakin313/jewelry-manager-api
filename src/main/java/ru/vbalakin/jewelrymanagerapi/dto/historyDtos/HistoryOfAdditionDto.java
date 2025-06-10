package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOfAdditionDto {

    @NonNull
    @Schema(description = "Client info")
    private HistoryClientInfoDto clientInfo;

    @NonNull
    @Schema(description = "History of adding decorations")
    private List<HistoryJewelryInfoDto> jewelriesInfo;

    @NonNull
    @Schema(description = "History of the addition of precious metals")
    private List<HistoryPreciousMetalInfoDto> preciousMetalsInfo;
}
