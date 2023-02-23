package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name="status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name="classificationid")
    private ClassificationEntity classification;

    @ManyToOne
    @JoinColumn(name="unitid")
    private UnitEntity unit;

    @ManyToOne
    @JoinColumn(name="locationid")
    private LocationEntity location;
}