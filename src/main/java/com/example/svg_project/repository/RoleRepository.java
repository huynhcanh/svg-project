package com.example.svg_project.repository;

import com.example.svg_project.entity.RoleEntity;
import com.example.svg_project.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
