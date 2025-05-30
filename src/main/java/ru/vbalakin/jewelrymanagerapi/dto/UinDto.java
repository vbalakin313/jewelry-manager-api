package ru.vbalakin.jewelrymanagerapi.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UinDto {
    @NonNull
    private String uin;

}
