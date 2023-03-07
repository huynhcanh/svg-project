package com.example.svg_project.service;

import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
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
}
