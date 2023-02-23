package com.example.svg_project.service.impl;

import com.example.svg_project.constant.SystemConstant;
import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.mapper.ClassificationMapper;
import com.example.svg_project.model.mapper.LocationMapper;
import com.example.svg_project.model.request.AddClassificationRequest;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.service.ClassificationService;
import com.example.svg_project.service.LocationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.FormatUtils;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationMapper locationMapper;

    @Override
    public List<LocationResponse> findAll() {
        List<LocationEntity> locationEntities = locationRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        return locationEntities.stream().map(item->locationMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    public LocationResponse createLocation(AddLocationRequest addLocationRequest) {
        LocationEntity locationEntity = LocationEntity.builder()
                .warehouse(addLocationRequest.getWarehouse())
                .rack(addLocationRequest.getRack())
                .tray(addLocationRequest.getTray())
                .status(SystemConstant.ACTIVE_STATUS)
                .build();
        try{
            locationEntity = locationRepository.save(locationEntity);
        }catch(Exception e){
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This location"));
        }
        return locationMapper.toResponse(locationEntity);
    }

    @Override
    public List<Long> deleteLocations(List<Long> ids) {
        List<LocationEntity> locationEntities = locationRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        Long idCheck = EntityUtils.isEntityIdsConstantIds(locationEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        locationEntities = locationRepository.findByIdIn(ids);
        for(LocationEntity locationEntity: locationEntities){
            locationEntity.setStatus(SystemConstant.INACTIVE_STATUS);
            locationEntity = locationRepository.save(locationEntity);
            listIdDelete.add(locationEntity.getId());
        }
        return listIdDelete;
    }
}