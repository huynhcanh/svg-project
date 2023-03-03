package com.example.svg_project.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemResponse {
    private Long id;
    private String name;
    private String color;
    private String remark;
    private UnitResponse unit;
    private ClassificationResponse classification;
}
