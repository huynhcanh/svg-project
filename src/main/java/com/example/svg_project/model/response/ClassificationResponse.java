package com.example.svg_project.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClassificationResponse {
    private Long id;
    private String code;
    private String value;
}
