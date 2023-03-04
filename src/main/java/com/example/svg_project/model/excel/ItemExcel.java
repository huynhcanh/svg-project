package com.example.svg_project.model.excel;

import com.example.svg_project.utils.GenerateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;

import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_WIDTH;

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
