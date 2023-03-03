package com.example.svg_project.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemLocationResponse {
    private Long id;
    private ItemResponse item;
    private LocationResponse location;
    private Integer quantity;
    private String modifiedBy;
}
