package com.example.svg_project.api;

import com.example.svg_project.model.request.AddClassificationRequest;
import com.example.svg_project.model.request.UpdateClassificationRequest;
import com.example.svg_project.model.response.ClassificationResponse;
import com.example.svg_project.service.ClassificationService;
import com.example.svg_project.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassificationApi {

    @Autowired
    ClassificationService classificationService;

    @GetMapping("/classifications")
    public List<ClassificationResponse> findAll() {
        return classificationService.findAll();
    }

    @PostMapping("/classifications")
    public ClassificationResponse createClassification(@Valid @RequestBody AddClassificationRequest addClassificationRequest) {
        return classificationService.createClassification(addClassificationRequest);
    }

    @PutMapping("/classifications")
    public ClassificationResponse updateClassification(@Valid @RequestBody UpdateClassificationRequest updateClassificationRequest) {
        return classificationService.updateClassification(updateClassificationRequest);
    }

    @DeleteMapping("/classifications")
    public Long deleteClassifications(@RequestBody Long id) {
        return classificationService.deleteClassification(id);
    }
}
