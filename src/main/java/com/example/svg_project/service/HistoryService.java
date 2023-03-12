package com.example.svg_project.service;

import com.example.svg_project.model.request.HistoryRequest;
import com.example.svg_project.model.response.HistoryResponse;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> history(HistoryRequest historyRequest);
    void exportHistories(HistoryRequest historyRequest);
}