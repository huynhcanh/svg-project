package com.example.svg_project.utils;

import com.example.svg_project.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    public static <T extends BaseEntity> long isEntityIdsConstantIds(List<T> entities, List<Long> ids) {
        List<Long> entityIds = new ArrayList<>();
        for(BaseEntity e: entities){
            entityIds.add(e.getId());
        }
        for(Long id: ids){
            if(!entityIds.contains(id)){
                return id;
            }
        }
        return -1;
    }
}
