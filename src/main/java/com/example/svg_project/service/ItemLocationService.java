package com.example.svg_project.service;

import com.example.svg_project.model.request.AddItemLocationRequest;
import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.request.MoveItemRequest;
import com.example.svg_project.model.response.HistoryResponse;
import com.example.svg_project.model.response.ItemLocationResponse;

import java.util.List;

public interface ItemLocationService {
    ItemLocationResponse createItemLocation(AddItemLocationRequest addItemLocationRequest);
    List<ItemLocationResponse> findAll();
    List<ItemLocationResponse> getItems(List<Long> ids);
    List<ItemLocationResponse> moveItemLocations(MoveItemRequest moveItemRequest);

}
