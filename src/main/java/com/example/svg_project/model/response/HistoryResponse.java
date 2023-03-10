package com.example.svg_project.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class HistoryResponse {
    private String createdDate;
    private String createdBy;
    private String event;
    private ItemResponse item;
    private Integer quantity;
    private String fromLocation;
    private String toLocation;
    private String note;
}