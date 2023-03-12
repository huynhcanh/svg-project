package com.example.svg_project.model.excel;

import com.example.svg_project.model.response.ItemResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HistoryExcel {
    private String createdDate;
    private String createdBy;
    private String event;
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private String fromToLocation;
    private String note;
}