package com.example.svg_project.api;

import com.example.svg_project.model.request.AddItemLocationRequest;
import com.example.svg_project.model.request.MoveItemRequest;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.service.ItemLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemLocationApi {

    @Autowired
    ItemLocationService itemLocationService;

    @PostMapping("/item-location")
    public ItemLocationResponse createItemLocation(@Valid @RequestBody AddItemLocationRequest addItemLocationRequest) {
        return itemLocationService.createItemLocation(addItemLocationRequest);
    }

    @GetMapping("/item-location")
    public List<ItemLocationResponse> findAll(){
        return itemLocationService.findAll();
    }

    @GetMapping("/item-location-by-ids")
    public List<ItemLocationResponse> getItems(@RequestParam List<Long> ids) {
        return itemLocationService.getItems(ids);
    }

    @PutMapping("/item-location-move")
    public List<ItemLocationResponse> moveItemLocations(@Valid @RequestBody MoveItemRequest moveItemRequest) {
        return itemLocationService.moveItemLocations(moveItemRequest);
    }
}