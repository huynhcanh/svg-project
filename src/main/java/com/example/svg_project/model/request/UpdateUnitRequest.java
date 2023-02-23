package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUnitRequest {
    @NotNull(message = "id is not null")
    private Long id;
    @NotBlank(message = "value is not null or empty")
    private String value;
}
