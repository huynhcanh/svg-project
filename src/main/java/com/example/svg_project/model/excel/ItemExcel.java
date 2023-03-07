package com.example.svg_project.model.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Setter
@Getter
@NoArgsConstructor
public class ItemExcel {
    private String classificationValue;
    private Long id;
    private String name;
    private String unitValue;
    private String color;
    private String remark;
    private BufferedImage qr;
}
