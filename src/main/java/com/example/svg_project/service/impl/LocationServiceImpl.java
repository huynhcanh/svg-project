package com.example.svg_project.service.impl;

import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.mapper.LocationMapper;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.repository.ItemLocationRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.service.LocationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    ItemLocationRepository itemLocationRepository;

    @Override
    public List<LocationResponse> findAll() {
        List<LocationEntity> locationEntities = locationRepository.findAll();
        return locationEntities.stream().map(item->locationMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LocationResponse createLocation(AddLocationRequest addLocationRequest) {
        LocationEntity locationEntity = LocationEntity.builder()
                .warehouse(addLocationRequest.getWarehouse())
                .rack(addLocationRequest.getRack())
                .tray(addLocationRequest.getTray())
                .build();
        try{
            locationEntity = locationRepository.save(locationEntity);
        }catch(Exception e){
            throw new ValueExistException(ExceptionUtils.exsitValueMessage("This location"));
        }
        return locationMapper.toResponse(locationEntity);
    }

    @Override
    @Transactional
    public List<Long> deleteLocations(List<Long> ids) {
        List<LocationEntity> locationEntities = locationRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(locationEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        // delete list location checked
        locationRepository.deleteByIdIn(ids);
        return ids;
    }

    @Override
    public List<String> findAllWarehouse() {
        return locationRepository.findAllWarehouse();
    }

    @Override
    public List<String> findAllRackByWarehouse(String warehouse) {
        return locationRepository.findAllRackByWarehouse(warehouse);
    }

    @Override
    public List<String> findAllTrayByWarehouseAndRack(String warehouse, String rack) {
        return locationRepository.findAllTrayByWarehouseAndRack(warehouse, rack);
    }
}