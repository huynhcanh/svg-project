package com.example.svg_project.model.response.page;

import com.example.svg_project.model.response.ItemResponse;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPage {
    private long recordsTotal;
    private List<ItemResponse> data;
}
