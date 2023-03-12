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

    @PostMapping("/histories/filter")
    public List<HistoryResponse> history(@RequestBody HistoryRequest historyRequest) {
        return historyService.history(historyRequest);
    }

    @PostMapping("/histories/export-excel")
    public void exportHistories(@RequestBody HistoryRequest historyRequest) {
        historyService.exportHistories(historyRequest);
    }
}