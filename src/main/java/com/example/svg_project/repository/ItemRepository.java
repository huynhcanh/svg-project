package com.example.svg_project.repository;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.repository.custom.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>, ItemRepositoryCustom {
    List<ItemEntity> findByIdIn(List<Long> ids);
    void deleteByIdIn(List<Long> ids);
}
