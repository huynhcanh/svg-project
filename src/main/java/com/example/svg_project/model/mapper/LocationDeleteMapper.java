package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.LocationDeleteEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.LocationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationDeleteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LocationDeleteEntity toEntity(LocationEntity locationEntity) {
        return modelMapper.map(locationEntity, LocationDeleteEntity.class);
    }
}