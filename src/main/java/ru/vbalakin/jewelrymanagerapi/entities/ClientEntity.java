package ru.vbalakin.jewelrymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String country;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "country_code_id")
    private ClientCountryCodeEntity clientCountryCode;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private UinEntity uin;

    public ClientEntity(UUID uuid, String petr, String petrovich, Gender gender, String russia, ClientCountryCodeEntity build) {
    }
}
