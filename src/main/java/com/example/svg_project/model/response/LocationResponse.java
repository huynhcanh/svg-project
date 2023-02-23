package com.example.svg_project.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LocationResponse {
    private Long id;
    private String warehouse;
    private String rack;
    private String tray;
}
