package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location",
        uniqueConstraints= @UniqueConstraint(columnNames= {"warehouse", "rack", "tray"}))
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationEntity extends BaseEntity{

    @Column(name="warehouse")
    private String warehouse;

    @Column(name="rack")
    private String rack;

    @Column(name="tray")
    private String tray;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<ItemLocationEntity> itemLocations;
}