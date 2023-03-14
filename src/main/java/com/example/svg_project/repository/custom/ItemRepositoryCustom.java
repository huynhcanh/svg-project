package com.example.svg_project.repository.custom;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.request.PageItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.model.response.page.PageItemResponse;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ClassificationResponse> findDistinctClassifications();
    List<Long> findDistinctIds();
    List<String> findDistinctNames();
    List<UnitResponse> findDistinctUnits();
    List<String> findDistinctColors();
    List<String> findDistinctRemarks();
    long getRecordsTotal(PageItemRequest pageItemRequest);
    List<ItemEntity> sortFilterPagingSearchItems(PageItemRequest pageItemRequest);
}