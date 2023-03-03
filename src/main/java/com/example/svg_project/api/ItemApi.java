package com.example.svg_project.api;

import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
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

    @GetMapping("/item")
    public ItemResponse getItem(@RequestParam Long id) {
        return itemService.getItem(id);
    }

    @PutMapping("/items")
    public ItemResponse updateItem(@Valid @RequestBody UpdateItemRequest updateItemRequest) {
        return itemService.updateItem(updateItemRequest);
    }

    @DeleteMapping("/items")
    public List<Long> updateItem(@RequestBody List<Long> ids) {
        return itemService.deleteItems(ids);
    }
}