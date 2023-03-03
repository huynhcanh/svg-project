package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class UpdateItemRequest {
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
}
