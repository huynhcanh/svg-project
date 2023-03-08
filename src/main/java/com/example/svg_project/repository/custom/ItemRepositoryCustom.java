package com.example.svg_project.repository.custom;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.mapper.ItemMapper;
import com.example.svg_project.model.request.SortAndFilterItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ClassificationResponse> findDistinctClassifications();
    List<Long> findDistinctIds();
    List<String> findDistinctNames();
    List<UnitResponse> findDistinctUnits();
    List<String> findDistinctColors();
    List<String> findDistinctRemarks();
    List<ItemEntity> sortAndFilterItems(SortAndFilterItemRequest sortAndfilter);
}