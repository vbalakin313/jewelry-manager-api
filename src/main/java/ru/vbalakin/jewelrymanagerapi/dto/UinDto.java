package ru.vbalakin.jewelrymanagerapi.dto;


import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UinDto {
    @NonNull
    private String uin;

}
