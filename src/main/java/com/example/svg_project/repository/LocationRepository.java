package com.example.svg_project.repository;

import com.example.svg_project.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    List<LocationEntity> findAllByStatus(int status);
    List<LocationEntity> findByIdIn(List<Long> ids);
}