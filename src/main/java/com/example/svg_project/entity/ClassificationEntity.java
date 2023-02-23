package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassificationEntity extends BaseEntity{
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name="status")
    private Integer status;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemEntity> items;
}
