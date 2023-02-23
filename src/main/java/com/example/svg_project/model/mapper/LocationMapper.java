package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.LocationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LocationResponse toResponse(LocationEntity entity) {
        return modelMapper.map(entity, LocationResponse.class);
    }

    public LocationEntity toEntity(AddLocationRequest addLocationRequest) {
        return modelMapper.map(addLocationRequest, LocationEntity.class);
    }
}
