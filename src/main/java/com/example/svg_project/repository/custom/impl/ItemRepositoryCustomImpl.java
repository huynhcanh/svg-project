package com.example.svg_project.repository.custom.impl;

import com.example.svg_project.entity.ItemEntity;
import com.example.svg_project.model.request.PageItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.repository.custom.ItemRepositoryCustom;
import com.example.svg_project.utils.FormatUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public String generateQuery(PageItemRequest pageItemRequest, String select){
        // Khởi tạo câu query
        StringBuilder query = new StringBuilder();
        query.append("SELECT ").append(select).append(" FROM ItemEntity i ");

        // Khởi tạo các biến để chứa query cho phần join, where, filter và sort
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
        StringBuilder filterQuery = new StringBuilder();
        StringBuilder sortQuery = new StringBuilder();

        Map<String, String> sort = pageItemRequest.getSort();
        if (sort != null) {
            for (String key : sort.keySet()) {
                String value = sort.get(key);
                if (key.equals("classification")) {
                    if(!joinQuery.toString().contains("INNER JOIN i.classification c")){
                        joinQuery.append(" INNER JOIN i.classification c");
                    }
                    sortQuery.append("c.value ").append(value);
                } else if (key.equals("unit")) {
                    if(!joinQuery.toString().contains("INNER JOIN i.unit u")){
                        joinQuery.append(" INNER JOIN i.unit u");
                    }
                    sortQuery.append("u.value ").append(value);
                } else {
                    sortQuery.append("i.").append(key).append(" ").append(value);
                }
                break;
            }
        }

        Map<String, List<String>> filter = pageItemRequest.getFilter();
        if(filter != null){
            for (String key : filter.keySet()) {
                List<String> values = filter.get(key);
                if (key.equals("classification")) {
                    if(!joinQuery.toString().contains("INNER JOIN i.classification c")){
                        joinQuery.append(" INNER JOIN i.classification c");
                    }
                    filterQuery.append("c.code IN (")
                            .append(FormatUtils.arrayToString(values))
                            .append(") AND ");
                } else if (key.equals("unit")) {
                    if(!joinQuery.toString().contains("INNER JOIN i.unit u")){
                        joinQuery.append(" INNER JOIN i.unit u");
                    }
                    filterQuery.append("u.code IN (")
                            .append(FormatUtils.arrayToString(values))
                            .append(") AND ");
                } else {
                    filterQuery.append("i.").append(key).append(" IN (")
                            .append(FormatUtils.arrayToString(values))
                            .append(") AND ");
                }
            }
        }

        String itemName = pageItemRequest.getItemName();
        if(!itemName.isEmpty()){
            filterQuery.append("i.name like '%").append(itemName)
                    .append("%'").append(" AND ");
        }

        // Thêm các phần query vào câu query chính
        if (joinQuery.length() > 0) {
            query.append(joinQuery);
        }

        if(filterQuery.toString().length() > 0){
            filterQuery.setLength(filterQuery.length() - 5); // remove the trailing " AND "
            whereQuery.append(" WHERE ");
            whereQuery.append(filterQuery);
            query.append(whereQuery);
        }

        if (sortQuery.toString().length() > 0) {
            sortQuery.insert(0, " ORDER BY ");
            query.append(sortQuery);
        }

        System.out.println(query.toString());
        return query.toString();
    }

    @Override
    public long getRecordsTotal(PageItemRequest pageItemRequest){
        String queryJPA = generateQuery(pageItemRequest, "count(i.id)");
        return (long) entityManager.createQuery(queryJPA).getSingleResult();
    }

    @Override
    public List<ItemEntity> sortFilterPagingSearchItems(PageItemRequest pageItemRequest) {
        String queryJPA = generateQuery(pageItemRequest, "i");

        int start = pageItemRequest.getStart();
        int length = pageItemRequest.getLength();

        List<ItemEntity> items = entityManager.createQuery(queryJPA, ItemEntity.class)
                .setFirstResult(start).setMaxResults(length).getResultList();
        return items;
    }
}
