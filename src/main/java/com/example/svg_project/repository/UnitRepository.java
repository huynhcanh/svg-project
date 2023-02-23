package com.example.svg_project.repository;

import com.example.svg_project.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    List<UnitEntity> findAllByStatus(int status);
    List<UnitEntity> findByIdIn(List<Long> ids);
    UnitEntity findByCode(String code);
}
