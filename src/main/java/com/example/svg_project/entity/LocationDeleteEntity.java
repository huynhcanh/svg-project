package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locationdelete")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDeleteEntity extends BaseEntity{

    @Column(name="warehouse")
    private String warehouse;

    @Column(name="rack")
    private String rack;

    @Column(name="tray")
    private String tray;
}