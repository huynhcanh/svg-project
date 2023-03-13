package com.example.svg_project.service;

import com.example.svg_project.model.request.AddClassificationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;

import java.util.List;

public interface ClassificationService {
    List<ClassificationResponse> findAll();
    ClassificationResponse createClassification(AddClassificationRequest addClassificationRequest);
    ClassificationResponse updateClassification(UpdateClassificationRequest updateClassificationRequest);
    Long deleteClassification(Long id);
}
