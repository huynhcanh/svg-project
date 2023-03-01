package com.example.svg_project.model.request;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.UnitEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class AddOrUpdateItemRequest {
    private Long id;
    @NotBlank(message = "classification code is not null or empty")
    private String classificationCode;
    @NotBlank(message = "unit code is not null or empty")
    private String unitCode;
    @NotBlank(message = "name is not null or empty")
    private String name;
    @NotBlank(message = "color is not null or empty")
    private String color;
    @NotBlank(message = "remark is not null or empty")
    private String remark;
    @NotBlank(message = "warehouse is not null or empty")
    private String warehouse;
    @NotBlank(message = "rack is not null or empty")
    private String rack;
    @NotBlank(message = "tray is not null or empty")
    private String tray;
    @NotNull(message = "quantity is not null")
    @Min(value = 0, message = "quantity can't be less than 0")
    private Integer quantity;
}
