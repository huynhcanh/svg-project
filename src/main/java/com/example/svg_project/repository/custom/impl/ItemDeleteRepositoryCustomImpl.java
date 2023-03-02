package com.example.svg_project.repository.custom.impl;

import com.example.svg_project.entity.ItemDeleteEntity;
import com.example.svg_project.repository.custom.ItemDeleteRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemDeleteRepositoryCustomImpl implements ItemDeleteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ItemDeleteEntity insert(ItemDeleteEntity itemDeleteEntity) {
        entityManager.persist(itemDeleteEntity);
        return itemDeleteEntity;
    }
}
