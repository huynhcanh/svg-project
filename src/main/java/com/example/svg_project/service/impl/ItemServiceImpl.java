package com.example.svg_project.service.impl;

import com.example.svg_project.constant.SystemConstant;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.mapper.ItemMapper;
import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.service.ItemService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<ItemResponse> findAll() {
        List<ItemEntity> itemEntities = itemRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        return itemEntities.stream().map(item->itemMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    public ItemResponse createOrUpdateItem(AddOrUpdateItemRequest addOrUpdateItemRequest) {
        ItemEntity itemEntity = itemMapper.toEntity(addOrUpdateItemRequest);
        return itemMapper.toResponse(itemRepository.save(itemEntity));
    }

    @Override
    public List<Long> deleteItems(List<Long> ids) {
        List<ItemEntity> itemEntities = itemRepository.findAllByStatus(SystemConstant.ACTIVE_STATUS);
        Long idCheck = EntityUtils.isEntityIdsConstantIds(itemEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }

        List<Long> listIdDelete = new ArrayList();
        itemEntities = itemRepository.findByIdIn(ids);
        for(ItemEntity itemEntity: itemEntities){
            itemEntity.setStatus(SystemConstant.INACTIVE_STATUS);
            itemEntity = itemRepository.save(itemEntity);
            listIdDelete.add(itemEntity.getId());
        }
        return listIdDelete;
    }
}