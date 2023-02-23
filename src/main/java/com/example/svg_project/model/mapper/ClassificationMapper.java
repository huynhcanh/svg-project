package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassificationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ClassificationResponse toResponse(ClassificationEntity entity) {
        ClassificationResponse classificationResponse = modelMapper.map(entity, ClassificationResponse.class);
        return classificationResponse;
    }

    public ClassificationEntity toEntity(UpdateClassificationRequest updateClassificationRequest) {
        return modelMapper.map(updateClassificationRequest, ClassificationEntity.class);
    }
}
