package com.example.svg_project.api;

import com.example.svg_project.model.request.UpdateUnitRequest;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.service.UnitService;
import com.example.svg_project.utils.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
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

    @PostMapping("/units")
    public UnitResponse createUnit(@Valid @RequestBody String valueRequest) {
        return unitService.createUnit(valueRequest);
    }

    @PutMapping("/units")
    public UnitResponse updateUnitn(@Valid @RequestBody UpdateUnitRequest updateUnitRequest) {
        return unitService.updateUnit(updateUnitRequest);
    }

    @DeleteMapping("/units")
    public List<Long> updateUnit(@RequestBody List<Long> ids) {
        return unitService.deleteUnits(ids);
    }
}