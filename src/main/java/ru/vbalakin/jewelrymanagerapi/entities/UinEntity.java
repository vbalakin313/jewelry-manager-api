package ru.vbalakin.jewelrymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

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

}
