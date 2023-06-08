package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity extends BaseEntity{

    @Column(length = 20)
    private String name;
}
