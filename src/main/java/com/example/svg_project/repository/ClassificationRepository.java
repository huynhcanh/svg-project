package com.example.svg_project.repository;

import com.example.svg_project.entity.ClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {
    List<ClassificationEntity> findAllByStatus(int status);
    List<ClassificationEntity> findByIdIn(List<Long> ids);
    ClassificationEntity findByCode(String code);
}
