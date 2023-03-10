package com.example.svg_project.service.impl;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.exception.NotFoundException;
import com.example.svg_project.exception.ValueExistException;
import com.example.svg_project.model.excel.LocationExcel;
import com.example.svg_project.model.mapper.HistoryMapper;
import com.example.svg_project.model.mapper.LocationMapper;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.response.HistoryResponse;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.repository.HistoryRepository;
import com.example.svg_project.repository.ItemLocationRepository;
import com.example.svg_project.repository.LocationRepository;
import com.example.svg_project.service.HistoryService;
import com.example.svg_project.service.LocationService;
import com.example.svg_project.utils.EntityUtils;
import com.example.svg_project.utils.ExcelUtils;
import com.example.svg_project.utils.ExceptionUtils;
import com.example.svg_project.utils.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public List<HistoryResponse> findAll() {
        List<HistoryEntity> historyEntities = historyRepository.findAllByCreatedDateDESC();
        return historyEntities.stream().map(item->historyMapper.toResponse(item)).collect(Collectors.toList());
    }

    @Override
    public List<HistoryResponse> history(HistoryRequest historyRequest) {
        System.out.println(historyRequest.getFromHistory() + "-" + historyRequest.getToHistory());
        return null;
    }
}