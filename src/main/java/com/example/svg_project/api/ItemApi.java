package com.example.svg_project.api;

import com.example.svg_project.model.request.AddOrUpdateItemRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.service.ClassificationService;
import com.example.svg_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemApi {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public List<ItemResponse> findAll() {
        return itemService.findAll();
    }

    @PostMapping("/items")
    public ItemResponse createItem(@Valid @RequestBody AddOrUpdateItemRequest addOrUpdateItemRequest) {
        return itemService.createOrUpdateItem(addOrUpdateItemRequest);
    }

    @PutMapping("/items")
    public ItemResponse updateItem(@Valid @RequestBody AddOrUpdateItemRequest addOrUpdateItemRequest) {
        return itemService.createOrUpdateItem(addOrUpdateItemRequest);
    }

    @DeleteMapping("/items")
    public List<Long> updateItem(@RequestBody List<Long> ids) {
        return itemService.deleteItems(ids);
    }
}