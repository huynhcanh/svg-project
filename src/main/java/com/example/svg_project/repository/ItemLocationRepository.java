package com.example.svg_project.repository;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.ItemLocationEntity;
import com.example.svg_project.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemLocationRepository extends JpaRepository<ItemLocationEntity, Long> {
    List<ItemLocationEntity> findByIdIn(List<Long> ids);
    boolean existsByItemAndLocation(ItemEntity itemEntity, LocationEntity locationEntity);
    ItemLocationEntity findByItemAndLocation(ItemEntity itemEntity, LocationEntity locationEntity);
}