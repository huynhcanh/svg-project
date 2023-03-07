package com.example.svg_project.model.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class AddItemExcelRequest {
    @NotBlank(message = "classification code is not null or empty")
    private String classificationCode;
    @NotBlank(message = "name is not null or empty")
    private String name;
    @NotBlank(message = "unit code is not null or empty")
    private String unitCode;
    @NotBlank(message = "color is not null or empty")
    private String color;
    @NotBlank(message = "remark is not null or empty")
    private String remark;
}
