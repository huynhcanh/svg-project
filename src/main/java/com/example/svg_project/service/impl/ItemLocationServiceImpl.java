package com.example.svg_project.service.impl;

import com.example.svg_project.constant.HistoryConstant;
import com.example.svg_project.entity.*;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.QuantityNotEnoughtException;
import com.example.svg_project.model.mapper.ItemLocationMapper;
import com.example.svg_project.model.request.AddItemLocationRequest;
import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.request.MoveItemRequest;
import com.example.svg_project.model.response.HistoryResponse;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.repository.*;
import com.example.svg_project.service.ItemLocationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.FormatUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.svg_project.constant.HistoryConstant.*;

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

    @Autowired
    private HistoryRepository historyRepository;

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
        // history
        String event = "";
        String toHistory = "";
        int quantityHistory = addItemLocationRequest.getQuantity();
        if(warehouse.equals(OUT) && rack.equals(OUT) && tray.equals(OUT)) {
            toHistory = event = OUT;
        } else {
            toHistory = FormatUtils.putWordsByChar('-',
                    locationEntity.getWarehouse(),
                    locationEntity.getRack(),
                    locationEntity.getTray());
            event = IN;
        }
        HistoryEntity historyEntity= HistoryEntity.builder()
                .event(event)
                .fromLocation("")
                .toLocation(toHistory)
                .item(itemEntity)
                .quantity(quantityHistory)
                .build();
        historyRepository.save(historyEntity);

        return itemLocationMapper.toResponse(itemLocationRepository.save(itemLocationEntity));
    }

    @Override
    public List<ItemLocationResponse> findAll() {
        List<ItemLocationEntity> itemLocationEntities = itemLocationRepository.findAllByLocationWarehouseNotEqual(OUT);
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

        // find location move
        String warehouse = moveItemRequest.getWarehouse();
        String rack = moveItemRequest.getRack();
        String tray = moveItemRequest.getTray();
        LocationEntity locationMove = locationRepository.findByWarehouseAndRackAndTray(
                warehouse, rack, tray);

        List<ItemLocationEntity> itemLocationEntities = new ArrayList<>();

        for(MoveItemRequest.MoveDetailRequest itemLocationReq: moveItemRequest.getItems()){

            ItemLocationEntity itemLocationEntity = itemLocationRepository.findById(itemLocationReq.getId()).get();

            int quantityMove = itemLocationReq.getQuantity();
            int quantityCurrent = itemLocationEntity.getQuantity();
            ItemEntity itemMove = itemLocationEntity.getItem();

            // check qty is allow?
            if(!isQuatityAllow(quantityCurrent, quantityMove))
                throw new QuantityNotEnoughtException(ExceptionUtils.qtyNotEnoughMessage(itemLocationReq.getId()));

            // only move items have location != current location
            if(itemLocationEntity.getLocation().getId() != locationMove.getId()){
                // from location & to location & event
                String fromLocation = FormatUtils.putWordsByChar('-',
                        itemLocationEntity.getLocation().getWarehouse(),
                        itemLocationEntity.getLocation().getRack(),
                        itemLocationEntity.getLocation().getTray());
                String toLocation = null;
                String event = null;

                // save item location current and get fromLocation
                itemLocationEntity.setQuantity(quantityCurrent - quantityMove);
                itemLocationRepository.save(itemLocationEntity);

                // if move to OUT
                if(warehouse.equals(OUT) && rack.equals(OUT) && tray.equals(OUT)){
                    ItemLocationEntity outItemLocation = ItemLocationEntity.builder()
                            .item(itemMove)
                            .location(locationMove)
                            .quantity(quantityMove)
                            .build();
                    itemLocationEntities.add(itemLocationRepository.save(outItemLocation));
                    toLocation = event = OUT;
                }
                else {
                    // move to position that item and location (#OUT) existed
                    ItemLocationEntity updateItemLocation = itemLocationRepository.findByItemAndLocation(itemMove, locationMove);
                    if(updateItemLocation != null){
                        updateItemLocation.setQuantity(updateItemLocation.getQuantity() + quantityMove);
                        itemLocationEntities.add(itemLocationRepository.save(updateItemLocation));
                        toLocation = FormatUtils.putWordsByChar('-',
                                updateItemLocation.getLocation().getWarehouse(),
                                updateItemLocation.getLocation().getRack(),
                                updateItemLocation.getLocation().getTray());
                    }
                    else{
                        // create new itemlocaiton with new location
                        ItemLocationEntity newItemLocation = ItemLocationEntity.builder()
                                .item(itemMove)
                                .location(locationMove)
                                .quantity(quantityMove)
                                .build();
                        itemLocationEntities.add(itemLocationRepository.save(newItemLocation));
                        toLocation = FormatUtils.putWordsByChar('-',
                                newItemLocation.getLocation().getWarehouse(),
                                newItemLocation.getLocation().getRack(),
                                newItemLocation.getLocation().getTray());
                    }
                    event = TRANSFERT;
                }
                // save history
                HistoryEntity historyEntity= HistoryEntity.builder()
                        .event(event)
                        .fromLocation(fromLocation)
                        .toLocation(toLocation)
                        .item(itemMove)
                        .quantity(quantityMove)
                        .build();
                historyRepository.save(historyEntity);
            }
        }
        return itemLocationEntities.stream().map(item->itemLocationMapper.toResponse(item)).collect(Collectors.toList());
    }
}