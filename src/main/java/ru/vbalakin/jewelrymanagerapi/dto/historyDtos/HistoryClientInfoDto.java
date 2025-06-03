package ru.vbalakin.jewelrymanagerapi.dto.historyDtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryClientInfoDto {
    @NonNull
    private String name;

    @NonNull
    private String surname;
}
