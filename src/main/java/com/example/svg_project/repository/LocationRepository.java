package com.example.svg_project.repository;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    @Query("SELECT l FROM LocationEntity l WHERE l.warehouse <> :warehouse")
    List<LocationEntity> findAllLocationByWarehouseNotLike(String warehouse);
    void deleteByIdIn(List<Long> ids);
    LocationEntity findByWarehouseAndRackAndTray(String warehouse, String rack, String tray);
    @Query("SELECT DISTINCT warehouse FROM LocationEntity")
    List<String> findAllWarehouse();
    @Query("SELECT DISTINCT warehouse FROM LocationEntity WHERE warehouse <> :warehouse")
    List<String> findAllWarehouseByWarehouseNotLike(String warehouse);
    @Query("SELECT DISTINCT rack FROM LocationEntity WHERE warehouse = :warehouse")
    List<String> findAllRackByWarehouse(@Param("warehouse") String warehouse);
    @Query("SELECT DISTINCT tray FROM LocationEntity WHERE warehouse = :warehouse AND rack = :rack")
    List<String> findAllTrayByWarehouseAndRack(@Param("warehouse") String warehouse, @Param("rack") String rack);
    List<LocationEntity> findByIdIn(List<Long> ids);

}