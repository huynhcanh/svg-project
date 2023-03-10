package com.example.svg_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryEntity extends BaseEntity{
    @Column(name="event")
    private String event;

    @ManyToOne
    @JoinColumn(name="itemid")
    private ItemEntity item;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="fromLocation")
    private String fromLocation;

    @Column(name="toLocation")
    private String toLocation;

    @Column(name="note")
    private String note;
}
