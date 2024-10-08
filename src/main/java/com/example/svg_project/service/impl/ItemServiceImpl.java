package com.example.svg_project.service.impl;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.ItemLocationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.model.excel.AddItemExcelRequest;
import com.example.svg_project.model.excel.ItemExcel;
import com.example.svg_project.model.mapper.ItemDeleteMapper;
import com.example.svg_project.model.mapper.ItemLocationMapper;
import com.example.svg_project.model.mapper.ItemMapper;
import com.example.svg_project.model.request.PageItemRequest;
import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.model.response.page.PageItemResponse;
import com.example.svg_project.repository.ItemDeleteRepository;
import com.example.svg_project.repository.ItemLocationRepository;
import com.example.svg_project.repository.ItemRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.service.ItemService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExcelUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    @Autowired
    ItemLocationRepository itemLocationRepository;

    @Autowired
    ItemLocationMapper itemLocationMapper;

    @Override
    public ItemResponse getItem(Long id) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(id);
        if(!optionalItemEntity.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }
        return itemMapper.toResponse(optionalItemEntity.get());
    }

    @Override
    @Transactional
    public ItemResponse updateItem(UpdateItemRequest updateItemRequest) {
        ItemEntity itemEntity = itemMapper.toEntity(updateItemRequest);
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
    public void exportExcelItems(List<Long> ids) {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        Long idCheck = EntityUtils.isEntityIdsConstantIds(itemEntities, ids);
        if(idCheck != -1){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id = " + idCheck));
        }
        List<ItemExcel> items = itemRepository.findByIdIn(ids).stream().
                map(item->itemMapper.toExcel(item)).collect(Collectors.toList());

        String[] headers = {"Classification", "Item ID", "Item name", "Unit", "Color", "Remark", "QR"};
        String sheetName = "Items";
        String downloadDirPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        OutputStream outputStream = null;
        try {
            String fileName = GenerateUtils.generateFileName(downloadDirPath + "items.xlsx");
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ExcelUtils.exportToExcel(items, headers, sheetName, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void loadAndSaveExcelItems(MultipartFile file) {
        List<AddItemExcelRequest> items = null;
        try {
            items = ExcelUtils.mapExcelDataToList(file.getInputStream(), AddItemExcelRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(items != null){
            for(AddItemExcelRequest item: items){
                itemRepository.save(itemMapper.toEntity(item));
            }
        }
    }

    @Override
    public List<ClassificationResponse> findDistinctClassifications() {
        return itemRepository.findDistinctClassifications();
    }

    @Override
    public List<Long> findDistinctIds() {
        return itemRepository.findDistinctIds();
    }

    @Override
    public List<String> findDistinctNames() {
        return itemRepository.findDistinctNames();
    }

    @Override
    public List<UnitResponse> findDistinctUnits() {
        return itemRepository.findDistinctUnits();
    }

    @Override
    public List<String> findDistinctColors() {
        return itemRepository.findDistinctColors();
    }

    @Override
    public List<String> findDistinctRemarks() {
        return itemRepository.findDistinctRemarks();
    }

    @Override
    public PageItemResponse sortFilterPagingSearchItems(PageItemRequest pageItemRequest) {
        List<ItemEntity> itemEntities = itemRepository.sortFilterPagingSearchItems(pageItemRequest);
        // get data
        List<ItemResponse> data = itemEntities.stream().map(item->itemMapper.toResponse(item)).collect(Collectors.toList());
        // get recordsTotal || recordsFiltered
        long recordsTotal = itemRepository.getRecordsTotal(pageItemRequest);

        PageItemResponse pageItemResponse = PageItemResponse.builder()
                .recordsTotal(recordsTotal)
                .data(data)
                .build();
        return pageItemResponse;
    }

    @Override
    public void dowloadFromExcelItems() {
        String[] headers = {"Classification code", "Item name", "Unit code", "Color", "Remark"};
        String sheetName = "Items";
        String downloadDirPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        OutputStream outputStream = null;
        try {
            String fileName = GenerateUtils.generateFileName(downloadDirPath + "form-add-items.xlsx");
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ExcelUtils.exportToExcel(new ArrayList<>(), headers, sheetName, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemLocationResponse> getListItemLocationByItemId(Long itemId) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);
        if(!optionalItemEntity.isPresent()){
            throw new NotFoundException(ExceptionUtils.notFoundMessage("id"));
        }
        List<ItemLocationEntity> itemLocationEntities =  optionalItemEntity.get().getItemLocations();
        return itemLocationEntities.stream().map(item->itemLocationMapper.toResponse(item)).collect(Collectors.toList());
    }
}