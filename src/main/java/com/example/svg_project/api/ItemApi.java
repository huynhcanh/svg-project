package com.example.svg_project.api;

import com.example.svg_project.model.request.UpdateItemRequest;
import com.example.svg_project.model.response.ItemResponse;
import com.example.svg_project.service.ItemService;
import com.example.svg_project.utils.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public List<Long> deleteItem(@RequestBody List<Long> ids) {
        return itemService.deleteItems(ids);
    }

    @GetMapping("/items/export-excel")
    public void exportExcelItems(@RequestBody List<Long> ids) {
        BufferedImage qrCode = GenerateUtils.generateQrCode("1", 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(qrCode, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //itemService.exportExcelItems(ids);
    }
}