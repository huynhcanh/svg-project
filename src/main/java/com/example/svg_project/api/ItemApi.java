package com.example.svg_project.api;

import com.example.svg_project.model.request.PageItemRequest;
import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.model.response.ItemLocationResponse;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.model.response.UnitResponse;
import com.example.svg_project.model.response.page.PageItemResponse;
import com.example.svg_project.service.ItemLocationService;
import com.example.svg_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemApi {

    @Autowired
    ItemService itemService;

    @GetMapping("/item")
    public ItemResponse getItem(@RequestParam Long id) {
        return itemService.getItem(id);
    }

    @PutMapping("/items")
    public ItemResponse updateItem(@Valid @RequestBody UpdateItemRequest updateItemRequest) {
        return itemService.updateItem(updateItemRequest);
    }

    @DeleteMapping("/items")
    public List<Long> deleteItem(@RequestBody List<Long> ids) {
        return itemService.deleteItems(ids);
    }

    @GetMapping("/items/export-excel")
    public void exportExcelItems(@RequestParam List<Long> ids) {
        itemService.exportExcelItems(ids);
    }

    @PostMapping("/items/import-excel")
    public void loadAndSaveExcelItems(@RequestParam("file") MultipartFile file) {
        if (!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")) {
            return;
        }
        itemService.loadAndSaveExcelItems(file);
    }

    @GetMapping("/items/distinct-classifications")
    public List<ClassificationResponse> distinctClassifications() {
        return itemService.findDistinctClassifications();
    }

    @GetMapping("/items/distinct-ids")
    public List<Long> findDistinctIds() {
        return itemService.findDistinctIds();
    }

    @GetMapping("/items/distinct-names")
    public List<String> findDistinctNames() {
        return itemService.findDistinctNames();
    }

    @GetMapping("/items/distinct-colors")
    public List<String> findDistinctColors() {
        return itemService.findDistinctColors();
    }

    @GetMapping("/items/distinct-remarks")
    public List<String> findDistinctRemarks() {
        return itemService.findDistinctRemarks();
    }

    @GetMapping("/items/distinct-units")
    public List<UnitResponse> distinctUnits() {
        return itemService.findDistinctUnits();
    }

    @PostMapping("/items/page-request")
    public PageItemResponse sortFilterPagingSearchItems(@RequestBody PageItemRequest pageItemRequest) {
        System.out.println(pageItemRequest.getStart() + "-" + pageItemRequest.getLength());
        return itemService.sortFilterPagingSearchItems(pageItemRequest);
    }

    @GetMapping("/items/dowload-form-excel")
    public void dowloadFromExcelItems() {
        itemService.dowloadFromExcelItems();
    }

    @GetMapping("/items/scan-qr")
    public List<ItemLocationResponse> getListItemLocationByItemId(@RequestParam Long itemId) {
        return itemService.getListItemLocationByItemId(itemId);
    }
}