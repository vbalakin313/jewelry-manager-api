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
@Table(name = "jewelries")
public class JewelryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String name;

    private String description;

    private double weight;

    @Enumerated(EnumType.STRING)
    private MetalType material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uin_id", referencedColumnName = "id")
    private UinEntity uin;
}
