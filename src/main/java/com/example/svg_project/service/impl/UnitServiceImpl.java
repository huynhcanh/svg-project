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
        List<UnitEntity> unitEntities = unitRepository.findAll();
        return unitEntities.stream().map(item->unitMapper.toResponse(item)).collect(Collectors.toList());
    }
}