package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDeleteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ItemDeleteEntity toEntity(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDeleteEntity.class);
    }
}