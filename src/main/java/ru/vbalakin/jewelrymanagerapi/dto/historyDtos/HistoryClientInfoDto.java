package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User entity in the transaction history")
public class HistoryClientInfoDto {
    @NonNull
    @Schema(description = "Name", example = "Ivan")
    private String name;

    @NonNull
    @Schema(description = "Surname", example = "Ivanov")
    private String surname;
}
