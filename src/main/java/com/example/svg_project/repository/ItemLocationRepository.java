package com.example.svg_project.repository;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.ItemLocationEntity;
import com.example.svg_project.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ItemLocationRepository extends JpaRepository<ItemLocationEntity, Long> {
    List<ItemLocationEntity> findByIdIn(List<Long> ids);
    ItemLocationEntity findByItemAndLocation(ItemEntity itemEntity, LocationEntity locationEntity);
    @Query("SELECT il FROM ItemLocationEntity il WHERE il.location.warehouse <> :warehouse")
    List<ItemLocationEntity> findAllByLocationWarehouseNotEqual(@Param("warehouse") String warehouse);
}