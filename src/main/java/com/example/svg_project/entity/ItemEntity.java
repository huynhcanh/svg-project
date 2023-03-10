package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntity extends BaseEntity{
    @Column(name="name")
    private String name;

    @Column(name="color")
    private String color;

    @Column(name="remark")
    private String remark;

    @ManyToOne
    @JoinColumn(name="classificationid")
    private ClassificationEntity classification;

    @ManyToOne
    @JoinColumn(name="unitid")
    private UnitEntity unit;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemLocationEntity> itemLocations;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<HistoryEntity> histories;
}