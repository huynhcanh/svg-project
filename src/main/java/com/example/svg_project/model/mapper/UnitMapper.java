package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.UnitEntity;
import com.example.svg_project.model.response.UnitResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UnitResponse toResponse(UnitEntity entity) {
        UnitResponse unitResponse = modelMapper.map(entity, UnitResponse.class);
        return unitResponse;
    }
}
