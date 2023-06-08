package com.example.svg_project.service;

import com.example.svg_project.entity.UserEntity;
import com.example.svg_project.model.response.UnitResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    UserEntity save(UserEntity userEntity);
}
