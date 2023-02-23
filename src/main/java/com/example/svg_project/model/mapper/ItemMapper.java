package com.example.svg_project.model.mapper;

import com.example.svg_project.constant.SystemConstant;
import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.UnitEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.repository.UnitRepository;
import com.example.svg_project.utils.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse toResponse(ItemEntity entity) {
        return modelMapper.map(entity, ItemResponse.class);
    }

    public ItemEntity toEntity(AddOrUpdateItemRequest addOrUpdateItemRequest) {

        ClassificationEntity classificationEntity =
                classificationRepository.findByCode(addOrUpdateItemRequest.getClassificationCode());
        if(classificationEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("classification code"));
        }
        UnitEntity unitEntity =
                unitRepository.findByCode(addOrUpdateItemRequest.getUnitCode());
        if(unitEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("unit code"));
        }

        ItemEntity itemEntity = null;
        Long itemRequestId = addOrUpdateItemRequest.getId();
        if(itemRequestId == null){ // add
            itemEntity = modelMapper.map(addOrUpdateItemRequest, ItemEntity.class);
            itemEntity.setStatus(SystemConstant.ACTIVE_STATUS);
        }else{
            Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemRequestId);
            if(!optionalItemEntity.isPresent()){
                throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
            }
            itemEntity = optionalItemEntity.get();
            itemEntity.setColor(addOrUpdateItemRequest.getColor());
            itemEntity.setName(addOrUpdateItemRequest.getName());
            itemEntity.setRemark(addOrUpdateItemRequest.getRemark());
        }
        itemEntity.setUnit(unitEntity);
        itemEntity.setClassification(classificationEntity);
        return itemEntity;
    }
}