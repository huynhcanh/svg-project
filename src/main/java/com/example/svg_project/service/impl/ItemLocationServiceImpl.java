package com.example.svg_project.service.impl;

import com.example.svg_project.entity.*;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.QuantityNotEnoughtException;
import com.example.svg_project.model.mapper.ItemLocationMapper;
import com.example.svg_project.model.request.AddItemLocationRequest;
import com.example.svg_project.model.request.MoveItemRequest;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.repository.*;
import com.example.svg_project.service.ItemLocationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemLocationServiceImpl implements ItemLocationService {

    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemLocationMapper itemLocationMapper;

    @Autowired
    private ItemLocationRepository itemLocationRepository;

    @Override
    @Transactional
    public ItemLocationResponse createItemLocation(AddItemLocationRequest addItemLocationRequest) {
        ClassificationEntity classificationEntity =
                classificationRepository.findByCode(addItemLocationRequest.getClassificationCode());
        if(classificationEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("classification code"));
        }

        UnitEntity unitEntity =
                unitRepository.findByCode(addItemLocationRequest.getUnitCode());
        if(unitEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("unit code"));
        }

        String warehouse = addItemLocationRequest.getWarehouse();
        String rack = addItemLocationRequest.getRack();
        String tray = addItemLocationRequest.getTray();
        LocationEntity locationEntity = locationRepository.findByWarehouseAndRackAndTray(
                warehouse, rack, tray);
        if(locationEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("This location"));
        }

        ItemEntity itemEntity = modelMapper.map(addItemLocationRequest, ItemEntity.class);
        itemEntity.setUnit(unitEntity);
        itemEntity.setClassification(classificationEntity);
        itemEntity = itemRepository.save(itemEntity);
        ItemLocationEntity itemLocationEntity = ItemLocationEntity.builder()
                .item(itemEntity)
                .location(locationEntity)
                .quantity(addItemLocationRequest.getQuantity())
                .build();
        return itemLocationMapper.toResponse(itemLocationRepository.save(itemLocationEntity));
    }

    @Override
    public List<ItemLocationResponse> findAll() {
        List<ItemLocationEntity> itemLocationEntities = itemLocationRepository.findAll();
        return itemLocationEntities.stream().map(item->itemLocationMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    public List<ItemLocationResponse> getItems(List<Long> ids) {
        List<ItemLocationEntity> itemLocationEntities = itemLocationRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(itemLocationEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        itemLocationEntities = itemLocationRepository.findByIdIn(ids);
        return itemLocationEntities.stream().map(item->itemLocationMapper.toResponse(item)).collect(Collectors.toList());
    }

    boolean isQuatityAllow(int qtyCurrent, int qtyReq){
        return (qtyCurrent >= qtyReq);
    }

    @Override
    @Transactional
    public List<ItemLocationResponse> moveItemLocations(MoveItemRequest moveItemRequest) {

        List<ItemLocationEntity> itemLocationEntities = new ArrayList<>();

        for(MoveItemRequest.MoveDetailRequest itemLocationReq: moveItemRequest.getItems()){

            ItemLocationEntity itemLocationEntity = itemLocationRepository.findById(itemLocationReq.getId()).get();

            int quantityMove = itemLocationReq.getQuantity();
            int quantityCurrent = itemLocationEntity.getQuantity();
            ItemEntity itemMove = itemLocationEntity.getItem();
            // find location move
            String warehouse = moveItemRequest.getWarehouse();
            String rack = moveItemRequest.getRack();
            String tray = moveItemRequest.getTray();
            LocationEntity locationMove = locationRepository.findByWarehouseAndRackAndTray(
                    warehouse, rack, tray);
            if(locationMove == null){
                locationMove = LocationEntity.builder()
                        .warehouse(warehouse)
                        .rack(rack)
                        .tray(tray)
                        .build();
                locationMove = locationRepository.save(locationMove);
            }

            // check qty is allow?
            if(!isQuatityAllow(quantityCurrent, quantityMove))
                throw new QuantityNotEnoughtException(ExceptionUtils.qtyNotEnoughMessage(itemLocationReq.getId()));

            // only move items have location != current location
            if(itemLocationEntity.getLocation().getId() != locationMove.getId()){
                // save item location current
                itemLocationEntity.setQuantity(quantityCurrent - quantityMove);
                itemLocationRepository.save(itemLocationEntity);

                boolean existsByItemAndLocation = itemLocationRepository.existsByItemAndLocation(itemMove, locationMove);
                if(existsByItemAndLocation){
                    ItemLocationEntity updateItemLocation = itemLocationRepository.findByItemAndLocation(itemMove, locationMove);
                    updateItemLocation.setQuantity(updateItemLocation.getQuantity() + quantityMove);
                    itemLocationEntities.add(itemLocationRepository.save(updateItemLocation));
                }else{
                    // reate new item locaiton with new location
                    ItemLocationEntity newItemLocation = ItemLocationEntity.builder()
                            .item(itemMove)
                            .location(locationMove)
                            .quantity(quantityMove)
                            .build();
                    itemLocationEntities.add(itemLocationRepository.save(newItemLocation));
                }
            }
        }
        return itemLocationEntities.stream().map(item->itemLocationMapper.toResponse(item)).collect(Collectors.toList());
    }
}