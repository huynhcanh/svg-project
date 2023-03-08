package com.example.svg_project.repository.custom.impl;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.mapper.ItemMapper;
import com.example.svg_project.model.request.SortAndFilterItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.repository.custom.ItemRepositoryCustom;
import com.example.svg_project.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ClassificationResponse> findDistinctClassifications() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT c.code, c.value FROM ClassificationEntity c, ItemEntity i ");
        query.append("WHERE c.id = i.classification.id");
        List<Object[]> resultList = entityManager.createQuery(query.toString()).getResultList();
        List<ClassificationResponse> classificationList = new ArrayList<>();

        for (Object[] result : resultList) {
            ClassificationResponse classification = new ClassificationResponse();
            classification.setCode((String) result[0]);
            classification.setValue((String) result[1]);
            classificationList.add(classification);
        }

        return classificationList;
    }

    @Override
    public List<Long> findDistinctIds() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id FROM ItemEntity");
        return entityManager.createQuery(query.toString(), Long.class).getResultList();
    }

    @Override
    public List<String> findDistinctNames() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT name FROM ItemEntity");
        return entityManager.createQuery(query.toString(), String.class).getResultList();
    }

    @Override
    public List<UnitResponse> findDistinctUnits() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT u.code, u.value FROM UnitEntity u, ItemEntity i ");
        query.append("WHERE u.id = i.unit.id");
        List<Object[]> resultList = entityManager.createQuery(query.toString()).getResultList();
        List<UnitResponse> unitList = new ArrayList<>();

        for (Object[] result : resultList) {
            UnitResponse unit = new UnitResponse();
            unit.setCode((String) result[0]);
            unit.setValue((String) result[1]);
            unitList.add(unit);
        }

        return unitList;
    }

    @Override
    public List<String> findDistinctColors() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT color FROM ItemEntity");
        return entityManager.createQuery(query.toString(), String.class).getResultList();
    }

    @Override
    public List<String> findDistinctRemarks() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT remark FROM ItemEntity");
        return entityManager.createQuery(query.toString(), String.class).getResultList();
    }

    @Override
    public List<ItemEntity> sortAndFilterItems(SortAndFilterItemRequest sortAndfilter) {
        // Lấy các thông tin filter và sort của các thuộc tính trong SortAndFilterItemRequest
        SortAndFilterItemRequest.SortAndFilter<String> classification = sortAndfilter.getClassification();
        SortAndFilterItemRequest.SortAndFilter<Long> id = sortAndfilter.getId();
        SortAndFilterItemRequest.SortAndFilter<String> name = sortAndfilter.getName();
        SortAndFilterItemRequest.SortAndFilter<String> unit = sortAndfilter.getUnit();
        SortAndFilterItemRequest.SortAndFilter<String> color = sortAndfilter.getColor();
        SortAndFilterItemRequest.SortAndFilter<String> remark = sortAndfilter.getRemark();

        // Khởi tạo câu query
        StringBuilder query = new StringBuilder();
        query.append("SELECT i FROM ItemEntity i ");

        // Khởi tạo các biến để chứa query cho phần join, where, filter và sort
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
        StringBuilder filterQuery = new StringBuilder();
        StringBuilder sortQuery = new StringBuilder();

        // Xử lý phần filter và sort cho thuộc tính classification
        if (classification != null) {
            // Thêm phần join vào câu query
            joinQuery.append("INNER JOIN i.classification c ");

            // Xử lý phần filter cho thuộc tính classification
            String[] filter = classification.getFilter();
            if (filter != null) {
                filterQuery.append("c.code IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }

            // Xử lý phần sort cho thuộc tính classification
            String sort = classification.getSort();
            if (sort != null) {
                sortQuery.append("c.value " + sort + ",");
            }
        }

        // Xử lý phần filter và sort cho thuộc tính id
        if (id != null) {
            // Xử lý phần filter cho thuộc tính id
            Long[] filter = id.getFilter();
            if (filter != null) {
                filterQuery.append("i.id IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }

            // Xử lý phần sort cho thuộc tính id
            String sort = id.getSort();
            if (sort != null) {
                sortQuery.append(" i.id " + sort + ",");
            }
        }

        // Xử lý phần filter và sort cho thuộc tính name
        if (name != null) {
            // Xử lý phần filter cho thuộc tính name
            String[] filter = name.getFilter();
            if (filter != null) {
                filterQuery.append("i.name IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }

            // Xử lý phần sort cho thuộc tính name
            String sort = name.getSort();
            if (sort != null) {
                sortQuery.append(" i.name " + sort + ",");
            }
        }

        if(unit != null) {
            joinQuery.append("INNER JOIN i.unit u ");
            String[] filter = unit.getFilter();
            if(filter != null){
                filterQuery.append("u.code IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }
            String sort = unit.getSort();
            if(sort != null){
                sortQuery.append(" u.value " + sort + ",");
            }
        }

        if(color != null) {
            String[] filter = color.getFilter();
            if(filter != null){
                filterQuery.append("i.color IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }
            String sort = color.getSort();
            if(sort != null){
                sortQuery.append(" i.color " + sort + ",");
            }
        }

        if(remark != null) {
            String[] filter = remark.getFilter();
            if(filter != null){
                filterQuery.append("i.remark IN (" + FormatUtils.arrayToString(filter) + ") AND ");
            }
            String sort = remark.getSort();
            if(sort != null){
                sortQuery.append(" i.remark " + sort + ",");
            }
        }

        if(!joinQuery.toString().isEmpty()){
            query.append(joinQuery);
        }

        if(filterQuery.toString().length() > 0){
            filterQuery.setLength(filterQuery.length() - 5); // remove the trailing " AND "
            whereQuery.append(" WHERE ");
            whereQuery.append(filterQuery);
            query.append(whereQuery);
        }

        if (sortQuery.toString().length() > 0) {
            // Remove the trailing comma and add the ORDER BY clause
            sortQuery.setLength(sortQuery.length() - 1);
            sortQuery.insert(0, " ORDER BY ");
            query.append(sortQuery);
        }

        System.out.println(query.toString());

        // Execute the query and retrieve the list of matching items
        List<ItemEntity> items = entityManager.createQuery(query.toString(), ItemEntity.class).getResultList();
        return items;
    }
}
