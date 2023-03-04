package com.example.svg_project.service;

import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.LocationResponse;

import java.util.List;

public interface LocationService {
    List<LocationResponse> findAll();
    LocationResponse createLocation(AddLocationRequest addLocationRequest);
    List<Long> deleteLocations(List<Long> ids);
    List<String> findAllWarehouse();
    List<String> findAllRackByWarehouse(String warehouse);
    List<String> findAllTrayByWarehouseAndRack(String warehouse, String rack);
}