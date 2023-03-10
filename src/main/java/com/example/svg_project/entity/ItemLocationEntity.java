package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item_location")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemLocationEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "itemid")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "locationid")
    private LocationEntity location;

    @Column(name = "quantity")
    private Integer quantity;
}
