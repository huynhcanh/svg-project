package com.example.svg_project.service.impl;

import com.example.svg_project.constant.SystemConstant;
import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.mapper.ClassificationMapper;
import com.example.svg_project.model.request.AddClassificationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.service.ClassificationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    ClassificationRepository classificationRepository;

    @Autowired
    ClassificationMapper classificationMapper;

    @Override
    public List<ClassificationResponse> findAll() {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        return classificationEntities.stream().map(item->classificationMapper.toResponse(item)).collect(Collectors.toList());
    }

    boolean isValueExsit(String value) {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        for(ClassificationEntity classificationEntity: classificationEntities) {
            if(classificationEntity.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ClassificationResponse createClassification(AddClassificationRequest addClassificationRequest) {
        String valueRequest = FormatUtils.formatValue(addClassificationRequest.getValue());
        if(isValueExsit(valueRequest)) {
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This value"));
        }
        ClassificationEntity classificationEntity = ClassificationEntity.builder()
                    .value(valueRequest)
                    .code(FormatUtils.valueToCode(valueRequest))
                    .status(SystemConstant.ACTIVE_STATUS)
                    .build();
        return classificationMapper.toResponse(
                classificationRepository.save(classificationEntity));
    }

    @Override
    public ClassificationResponse updateClassification(UpdateClassificationRequest updateClassificationRequest) {
        Long idRequest = updateClassificationRequest.getId();
        Optional<ClassificationEntity> classificationEntityOptional = classificationRepository.findById(idRequest);
        if(!classificationEntityOptional.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }
        String valueRequest = FormatUtils.formatValue(updateClassificationRequest.getValue());
        if(isValueExsit(valueRequest)) {
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This value"));
        }
        ClassificationEntity classificationEntity = classificationEntityOptional.get();
        classificationEntity.setValue(valueRequest);
        classificationEntity.setCode(FormatUtils.valueToCode(valueRequest));
        return classificationMapper.toResponse(
                classificationRepository.save(classificationEntity));
    }

    @Override
    public List<Long> deleteClassifications(List<Long> ids) {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        Long idCheck = EntityUtils.isEntityIdsConstantIds(classificationEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        classificationEntities = classificationRepository.findByIdIn(ids);
        for(ClassificationEntity classificationEntity: classificationEntities){
            classificationEntity.setStatus(SystemConstant.INACTIVE_STATUS);
            classificationEntity = classificationRepository.save(classificationEntity);
            listIdDelete.add(classificationEntity.getId());
        }
        return listIdDelete;
    }
}