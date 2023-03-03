package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.*;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.request.AddItemLocationRequest;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.repository.UnitRepository;
import com.example.svg_project.utils.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemLocationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ItemLocationResponse toResponse(ItemLocationEntity entity) {
        return modelMapper.map(entity, ItemLocationResponse.class);
    }
}