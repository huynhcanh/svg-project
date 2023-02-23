package com.example.svg_project.service.impl;

import com.example.svg_project.constant.SystemConstant;
import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.UnitEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.mapper.UnitMapper;
import com.example.svg_project.model.request.UpdateUnitRequest;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.repository.UnitRepository;
import com.example.svg_project.service.UnitService;
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
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    UnitMapper unitMapper;

    @Override
    public List<UnitResponse> findAll() {
        List<UnitEntity> unitEntities = unitRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        return unitEntities.stream().map(item->unitMapper.toResponse(item)).collect(Collectors.toList());
    }

    boolean isValueExsit(String value) {
        List<UnitEntity> unitEntities = unitRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        for(UnitEntity unitEntity: unitEntities) {
            if(unitEntity.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UnitResponse createUnit(String valueRequest) {
        valueRequest = FormatUtils.formatValue(valueRequest);
        if(isValueExsit(valueRequest)) {
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This value"));
        }
        UnitEntity unitEntity = UnitEntity.builder()
                    .value(valueRequest)
                    .code(FormatUtils.valueToCode(valueRequest))
                    .status(SystemConstant.ACTIVE_STATUS)
                    .build();
        return unitMapper.toResponse(
                unitRepository.save(unitEntity));
    }

    @Override
    public UnitResponse updateUnit(UpdateUnitRequest updateUnitRequest) {
        Long idRequest = updateUnitRequest.getId();
        Optional<UnitEntity> UnitEntityOptional = unitRepository.findById(idRequest);
        if(!UnitEntityOptional.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }
        String valueRequest = FormatUtils.formatValue(updateUnitRequest.getValue());
        if(isValueExsit(valueRequest)) {
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This value"));
        }
        UnitEntity UnitEntity = UnitEntityOptional.get();
        UnitEntity.setValue(valueRequest);
        UnitEntity.setCode(FormatUtils.valueToCode(valueRequest));
        return unitMapper.toResponse(
                unitRepository.save(UnitEntity));
    }

    @Override
    public List<Long> deleteUnits(List<Long> ids) {
        List<UnitEntity> unitEntities = unitRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        Long idCheck = EntityUtils.isEntityIdsConstantIds(unitEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        unitEntities = unitRepository.findByIdIn(ids);
        for(UnitEntity UnitEntity: unitEntities){
            UnitEntity.setStatus(SystemConstant.INACTIVE_STATUS);
            UnitEntity = unitRepository.save(UnitEntity);
            listIdDelete.add(UnitEntity.getId());
        }
        return listIdDelete;
    }
}