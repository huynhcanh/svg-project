package com.example.svg_project.service.impl;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.mapper.ClassificationMapper;
import com.example.svg_project.model.request.AddClassificationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.service.ClassificationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<ClassificationResponse> findAll() {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAll();
        return classificationEntities.stream().map(item->classificationMapper.toResponse(item)).collect(Collectors.toList());
    }

    boolean isValueExsit(String value) {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAll();
        for(ClassificationEntity classificationEntity: classificationEntities) {
            if(classificationEntity.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public ClassificationResponse createClassification(AddClassificationRequest addClassificationRequest) {
        String valueRequest = FormatUtils.formatValue(addClassificationRequest.getValue());
        if(isValueExsit(valueRequest)) {
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This value"));
        }
        ClassificationEntity classificationEntity = ClassificationEntity.builder()
                    .value(valueRequest)
                    .code(FormatUtils.valueToCode(valueRequest))
                    .build();
        return classificationMapper.toResponse(
                classificationRepository.save(classificationEntity));
    }

    @Override
    @Transactional
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
    @Transactional
    public List<Long> deleteClassifications(List<Long> ids) {
        List<ClassificationEntity> classificationEntities = classificationRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(classificationEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        classificationEntities = classificationRepository.findByIdIn(ids);
        for(ClassificationEntity classificationEntity: classificationEntities){
            // set location in all item to null
            List<ItemEntity> itemEntities = classificationEntity.getItems();
            for(ItemEntity itemEntity: itemEntities){
                itemEntity.setClassification(null);
                itemRepository.save(itemEntity);
            }
            // add list result
            listIdDelete.add(classificationEntity.getId());
        }
        // delete list location checked
        classificationRepository.deleteByIdIn(ids);
        return listIdDelete;
    }
}