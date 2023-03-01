package com.example.svg_project.service;

import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;

import java.util.List;

public interface ItemService {
    List<ItemResponse> findAll();
    ItemResponse createOrUpdateItem(AddOrUpdateItemRequest addOrUpdateItemRequest);
    List<Long> deleteItems(List<Long> ids);
    ItemResponse getItem(Long id);
    List<ItemResponse> getItems(List<Long> ids);
}
