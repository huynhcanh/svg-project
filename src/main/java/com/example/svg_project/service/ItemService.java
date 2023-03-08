package com.example.svg_project.service;

import com.example.svg_project.model.request.SortAndFilterItemRequest;
import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    List<ItemResponse> findAll();
    ItemResponse getItem(Long id);
    ItemResponse updateItem(UpdateItemRequest updateItemRequest);
    List<Long> deleteItems(List<Long> ids);
    void exportExcelItems(List<Long> ids);
    void loadAndSaveExcelItems(MultipartFile file);
    List<ClassificationResponse> findDistinctClassifications();
    List<Long> findDistinctIds();
    List<String> findDistinctNames();
    List<UnitResponse> findDistinctUnits();
    List<String> findDistinctColors();
    List<String> findDistinctRemarks();
    List<ItemResponse> sortAndFilterItems(SortAndFilterItemRequest sortAndfilter);
}
