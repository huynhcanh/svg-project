package com.example.svg_project.service;

import com.example.svg_project.model.request.PageItemRequest;
import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.model.response.page.PageItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
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
    PageItemResponse sortFilterPagingSearchItems(PageItemRequest pageItemRequest);
    void dowloadFromExcelItems();
    List<ItemLocationResponse> getListItemLocationByItemId(Long itemId);
}
