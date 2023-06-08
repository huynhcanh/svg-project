package com.example.svg_project.service;

import com.example.svg_project.entity.RoleEntity;
import com.example.svg_project.enums.RoleEnum;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findByName(String name);
}
