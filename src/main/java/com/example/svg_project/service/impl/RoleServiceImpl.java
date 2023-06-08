package com.example.svg_project.service.impl;

import com.example.svg_project.entity.RoleEntity;
import com.example.svg_project.enums.RoleEnum;
import com.example.svg_project.repository.RoleRepository;
import com.example.svg_project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByName(String name) {
        return roleRepository.findByName(name);
    }
}