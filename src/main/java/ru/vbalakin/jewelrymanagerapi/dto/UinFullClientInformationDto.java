package ru.vbalakin.jewelrymanagerapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Unique UIN", example = "8402552457377752", accessMode = Schema.AccessMode.READ_ONLY)
    private String uin;

    @Schema(description = "Full information about the client")
    private ClientDto client;

    @Schema(description = "Full information about the client's jewelry")
    private List<JewelryDto> jewelries;

    @Schema(description = "Full information about the client's precious metals")
    private List<PreciousMetalDto> preciousMetals;
}
