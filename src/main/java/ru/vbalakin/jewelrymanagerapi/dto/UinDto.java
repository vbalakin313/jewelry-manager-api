package ru.vbalakin.jewelrymanagerapi.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UinDto {
    @NonNull
    @Schema(description = "Unique UIN", example = "8402552457377752", accessMode = Schema.AccessMode.READ_ONLY)
    private String uin;

}
