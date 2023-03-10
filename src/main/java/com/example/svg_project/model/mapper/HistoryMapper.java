package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.model.excel.LocationExcel;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.HistoryResponse;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.utils.DateUtils;
import com.example.svg_project.utils.GenerateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_HEIGHT_EXCEL;
import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_WIDTH_EXCEL;

@Component
public class HistoryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public HistoryResponse toResponse(HistoryEntity entity) {
        HistoryResponse historyResponse = modelMapper.map(entity, HistoryResponse.class);
        historyResponse.setCreatedDate(DateUtils.formatDate(entity.getCreatedDate()));
        return historyResponse;
    }
}
