package com.example.svg_project.repository;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.entity.LocationEntity;
import com.example.svg_project.repository.custom.HistoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long>,
        HistoryRepositoryCustom {

}