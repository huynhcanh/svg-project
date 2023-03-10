package com.example.svg_project.api;

import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.response.HistoryResponse;
import com.example.svg_project.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HistoryApi {

    @Autowired
    HistoryService historyService;

    @GetMapping("/histories")
    public List<HistoryResponse> findAll(){
        return historyService.findAll();
    }

    @PostMapping("/histories/filter")
    public List<HistoryResponse> history(@Valid @RequestBody HistoryRequest historyRequest) {
        return historyService.history(historyRequest);
    }
}