package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.*;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.repository.UnitRepository;
import com.example.svg_project.utils.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemDeleteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ItemDeleteEntity toEntity(ItemEntity itemEntity) {
        ItemDeleteEntity itemDeleteEntity = modelMapper.map(itemEntity, ItemDeleteEntity.class);
        itemDeleteEntity.setClassificationCode(itemEntity.getClassification().getCode());
        itemDeleteEntity.setLocationId(itemEntity.getLocation().getId());
        itemDeleteEntity.setUnitCode(itemEntity.getUnit().getCode());
        return itemDeleteEntity;
    }
}