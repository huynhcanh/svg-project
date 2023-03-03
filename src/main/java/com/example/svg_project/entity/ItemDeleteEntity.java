package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "itemdelete")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDeleteEntity extends BaseEntity{
    @Column(name="name")
    private String name;

    @Column(name="color")
    private String color;

    @Column(name="remark")
    private String remark;

    @Column(name="classificationcode")
    private String classificationCode;

    @Column(name="unitcode")
    private String unitCode;
}