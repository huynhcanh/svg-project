package com.example.svg_project.repository.custom;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.request.SortAndFilterItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.UnitResponse;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryRepositoryCustom {
    List<HistoryEntity> filterHistory(HistoryRequest historyRequest);
}