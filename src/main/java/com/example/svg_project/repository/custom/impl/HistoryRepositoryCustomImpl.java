package com.example.svg_project.repository.custom.impl;

import com.example.svg_project.entity.HistoryEntity;
import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.request.SortAndFilterItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.repository.custom.HistoryRepositoryCustom;
import com.example.svg_project.repository.custom.ItemRepositoryCustom;
import com.example.svg_project.utils.FormatUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class HistoryRepositoryCustomImpl implements HistoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HistoryEntity> filterHistory(HistoryRequest historyRequest) {
        String fromHistory = historyRequest.getFromHistory();
        String toHistory = historyRequest.getToHistory();

        StringBuilder query = new StringBuilder("SELECT h FROM HistoryEntity h ");
        StringBuilder filter = new StringBuilder();
        if(!fromHistory.isEmpty() && !toHistory.isEmpty()){
            filter.append(" WHERE Date(h.createdDate) BETWEEN '").append(fromHistory).append("' AND '").append(toHistory).append("'");
        } else if(!fromHistory.isEmpty() && toHistory.isEmpty()){
            filter.append(" WHERE Date(h.createdDate) >= '").append(fromHistory).append("'");
        } else if(fromHistory.isEmpty() && !toHistory.isEmpty()){
            filter.append(" WHERE Date(h.createdDate) <= '").append(toHistory).append("'");
        }

        if(filter.length()> 0){
            query.append(filter);
        }

        query.append(" ORDER BY h.createdDate DESC");

        System.out.println(query.toString());

        List<HistoryEntity> historyEntities = entityManager.createQuery(query.toString(), HistoryEntity.class).getResultList();
        return historyEntities;
    }
}
