package com.example.svg_project.service;

import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.LocationResponse;

import java.util.List;

public interface LocationService {
    List<LocationResponse> findAll(Boolean isOUT);
    LocationResponse createLocation(AddLocationRequest addLocationRequest);
    List<Long> deleteLocations(List<Long> ids);
    List<String> findAllWarehouse(Boolean isOUT);
    List<String> findAllRackByWarehouse(String warehouse);
    List<String> findAllTrayByWarehouseAndRack(String warehouse, String rack);
    void exportExcelLocations(List<Long> ids);
}