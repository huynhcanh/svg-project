package com.example.svg_project.service;

import com.example.svg_project.model.request.UpdateUnitRequest;
import com.example.svg_project.model.response.UnitResponse;

import java.util.List;

public interface UnitService {
    List<UnitResponse> findAll();
}
