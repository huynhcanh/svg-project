package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.UnitEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.excel.AddItemExcelRequest;
import com.example.svg_project.model.excel.ItemExcel;
import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.repository.ClassificationRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.repository.UnitRepository;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.GenerateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.svg_project.constant.QrConstant.*;

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

    public ItemEntity toEntity(UpdateItemRequest updateItemRequest) {

        ClassificationEntity classificationEntity =
                classificationRepository.findByCode(updateItemRequest.getClassificationCode());
        if(classificationEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("classification code"));
        }
        UnitEntity unitEntity =
                unitRepository.findByCode(updateItemRequest.getUnitCode());
        if(unitEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("unit code"));
        }

        Long itemRequestId = updateItemRequest.getId();
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemRequestId);
        if(!optionalItemEntity.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }

        ItemEntity itemEntity = modelMapper.map(updateItemRequest, ItemEntity.class);

        itemEntity.setUnit(unitEntity);
        itemEntity.setClassification(classificationEntity);
        return itemEntity;
    }

    public ItemExcel toExcel(ItemEntity entity) {
        ItemExcel itemExcel = modelMapper.map(entity, ItemExcel.class);
        itemExcel.setClassificationValue(entity.getClassification().getValue());
        itemExcel.setUnitValue(entity.getUnit().getValue());
        itemExcel.setQr(GenerateUtils.generateQrCode(entity.getId().toString(), QR_CODE_SIZE_WIDTH_EXCEL, QR_CODE_SIZE_HEIGHT_EXCEL));
        return itemExcel;
    }

    public ItemEntity toEntity(AddItemExcelRequest addItemExcelRequest) {
        ClassificationEntity classificationEntity =
                classificationRepository.findByCode(addItemExcelRequest.getClassificationCode());
        if(classificationEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("classification code"));
        }
        UnitEntity unitEntity =
                unitRepository.findByCode(addItemExcelRequest.getUnitCode());
        if(unitEntity == null){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("unit code"));
        }
        ItemEntity entity = modelMapper.map(addItemExcelRequest, ItemEntity.class);
        entity.setClassification(classificationEntity);
        entity.setUnit(unitEntity);
        return entity;
    }
}