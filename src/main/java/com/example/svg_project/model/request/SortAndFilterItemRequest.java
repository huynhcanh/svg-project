package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


@Setter
@Getter
@NoArgsConstructor
public class SortAndFilterItemRequest {
    private Map<String, String> sort;
    private Map<String, List<String>> filter;
}
