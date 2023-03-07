package com.example.svg_project.model.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Setter
@Getter
@NoArgsConstructor
public class LocationExcel {
    private String warehouse;
    private String rack;
    private String tray;
    private BufferedImage qr;
}
