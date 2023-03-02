package com.example.svg_project.repository;

import com.example.svg_project.entity.ItemDeleteEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.repository.custom.ItemDeleteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDeleteRepository extends JpaRepository<ItemDeleteEntity, Long>,
        ItemDeleteRepositoryCustom {

}
