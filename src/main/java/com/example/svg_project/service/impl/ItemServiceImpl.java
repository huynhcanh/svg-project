package com.example.svg_project.service.impl;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.QuantityNotEnoughtException;
import com.example.svg_project.model.mapper.ItemDeleteMapper;
import com.example.svg_project.model.mapper.ItemMapper;
import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.request.MoveItemRequest;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.repository.ItemDeleteRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.service.ItemService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ItemDeleteRepository itemDeleteRepository;

    @Autowired
    ItemDeleteMapper itemDeleteMapper;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<ItemResponse> findAll() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        return itemEntities.stream().map(item->itemMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemResponse createOrUpdateItem(AddOrUpdateItemRequest addOrUpdateItemRequest) {
        ItemEntity itemEntity = itemMapper.toEntity(addOrUpdateItemRequest);
        return itemMapper.toResponse(itemRepository.save(itemEntity));
    }

    @Override
    @Transactional
    public List<Long> deleteItems(List<Long> ids) {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(itemEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        itemEntities = itemRepository.findByIdIn(ids);
        for(ItemEntity itemEntity: itemEntities){
            // coppy to location delete table
            itemDeleteRepository.save(itemDeleteMapper.toEntity(itemEntity));
            // add list result
            listIdDelete.add(itemEntity.getId());
        }
        // delete list location checked
        itemRepository.deleteByIdIn(ids);
        return listIdDelete;
    }

    @Override
    public ItemResponse getItem(Long id) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(id);
        if(!optionalItemEntity.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }
        return itemMapper.toResponse(optionalItemEntity.get());
    }

    @Override
    public List<ItemResponse> getItems(List<Long> ids) {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(itemEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        itemEntities = itemRepository.findByIdIn(ids);
        return itemEntities.stream().map(item->itemMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemResponse> moveItems(MoveItemRequest moveItemRequest) {
        String warehouse = moveItemRequest.getWarehouse();
        String rack = moveItemRequest.getRack();
        String tray = moveItemRequest.getTray();
        LocationEntity locationEntity = locationRepository.findByWarehouseAndRackAndTray(
                warehouse, rack, tray);
        if(locationEntity == null){
            locationEntity = LocationEntity.builder()
                    .warehouse(warehouse)
                    .rack(rack)
                    .tray(tray)
                    .build();
            locationEntity = locationRepository.save(locationEntity);
        }
        List<ItemEntity> itemEntities = new ArrayList<>();
        for(MoveItemRequest.MoveDetailRequest item: moveItemRequest.getItems()){
            ItemEntity itemEntity = itemRepository.findById(item.getId()).get();
            int remainQuantity = itemEntity.getQuantity() - item.getQuantity();
            if(remainQuantity<0)
                throw new QuantityNotEnoughtException(ExceptionUtils.qtyNotEnoughMessage(item.getId()));
            if(itemEntity.getLocation().getId() != locationEntity.getId()){
                // save item current
                itemEntity.setQuantity(remainQuantity);
                itemRepository.save(itemEntity);
                // reate new item with new location
                itemEntities.add(itemRepository.save(itemMapper.toEntity(itemEntity, locationEntity, item.getQuantity())));
            }
        }
        return itemEntities.stream().map(item->itemMapper.toResponse(item)).collect(Collectors.toList());
    }
}