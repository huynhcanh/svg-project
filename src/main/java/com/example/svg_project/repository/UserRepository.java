package com.example.svg_project.repository;

import com.example.svg_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String userName);
    Boolean existsByUsername(String userName);
    Boolean existsByEmail(String email);
}
