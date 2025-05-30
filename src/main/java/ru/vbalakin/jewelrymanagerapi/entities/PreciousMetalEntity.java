package ru.vbalakin.jewelrymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.vbalakin.jewelrymanagerapi.domain.enums.MetalType;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "precious_metals")
public class PreciousMetalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MetalType metalType;

    private Double weight;

    private String assay;

    private String form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uin_id", referencedColumnName = "id")
    private UinEntity uin;
}
