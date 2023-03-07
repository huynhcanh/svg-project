package com.example.svg_project.model.mapper;

import com.example.svg_project.entity.ClassificationEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.model.excel.ItemExcel;
import com.example.svg_project.model.excel.LocationExcel;
import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.utils.GenerateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_HEIGHT_EXCEL;
import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_WIDTH_EXCEL;

@Component
public class LocationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LocationResponse toResponse(LocationEntity entity) {
        return modelMapper.map(entity, LocationResponse.class);
    }

    public LocationEntity toEntity(AddLocationRequest addLocationRequest) {
        return modelMapper.map(addLocationRequest, LocationEntity.class);
    }

    public LocationExcel toExcel(LocationEntity entity) {
        LocationExcel locationExcel = modelMapper.map(entity, LocationExcel.class);
        locationExcel.setQr(GenerateUtils.generateQrCode(entity.getId().toString(), QR_CODE_SIZE_WIDTH_EXCEL, QR_CODE_SIZE_HEIGHT_EXCEL));
        return locationExcel;
    }
}
