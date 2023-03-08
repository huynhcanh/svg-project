package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class SortAndFilterItemRequest {
    private SortAndFilter<String> classification;
    private SortAndFilter<Long> id;
    private SortAndFilter<String> name;
    private SortAndFilter<String> unit;
    private SortAndFilter<String> color;
    private SortAndFilter<String> remark;

    @Setter
    @Getter
    public static class SortAndFilter<T>{
        private String sort;
        private T filter[];
    }
}
