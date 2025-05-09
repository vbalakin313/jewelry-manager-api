package ru.vbalakin.jewelrymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "uin")
public class UinEntity {

    @Id
    private UUID id;

    private String uin;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private ClientEntity client;

    @OneToMany(mappedBy = "uin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JewelryEntity> jewelries = new ArrayList<>();

    @OneToMany(mappedBy = "uin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreciousMetalEntity> preciousMetals = new ArrayList<>();
}
