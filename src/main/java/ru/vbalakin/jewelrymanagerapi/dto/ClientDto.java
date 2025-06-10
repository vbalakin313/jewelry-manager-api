package ru.vbalakin.jewelrymanagerapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    @NonNull
    @Schema(description = "Unique client identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NonNull
    @Schema(description = "Client name")
    private String name;

    @NonNull
    @Schema(description = "Client surname")
    private String surname;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Client gender", example = "MALE")
    private Gender gender;

    @NonNull
    @Schema(description = "Country name", example = "Russia")
    private String country;

}
