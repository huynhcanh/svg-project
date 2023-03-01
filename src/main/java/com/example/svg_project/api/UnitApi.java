package com.example.svg_project.api;

import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UnitApi {

    @Autowired
    UnitService unitService;

    @GetMapping("/units")
    public List<UnitResponse> findAll() {
        return unitService.findAll();
    }
}