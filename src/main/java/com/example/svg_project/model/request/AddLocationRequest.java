package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class AddLocationRequest {
    @NotBlank(message = "warehouse is not null or empty")
    private String warehouse;
    @NotBlank(message = "rack is not null or empty")
    private String rack;
    @NotBlank(message = "tray is not null or empty")
    private String tray;
}
