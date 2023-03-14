package com.example.svg_project.repository.custom;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.model.request.HistoryRequest;

import java.util.List;

public interface HistoryRepositoryCustom {
    List<HistoryEntity> filterHistory(HistoryRequest historyRequest);
}