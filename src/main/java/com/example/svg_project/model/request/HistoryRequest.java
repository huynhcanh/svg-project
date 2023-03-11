package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class HistoryRequest {
    private String fromHistory;
    private String toHistory;
}
