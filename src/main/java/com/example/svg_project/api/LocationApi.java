package com.example.svg_project.api;

import com.example.svg_project.model.request.AddLocationRequest;
import com.example.svg_project.model.response.LocationResponse;
import com.example.svg_project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationApi {

    @Autowired
    LocationService locationService;

    @GetMapping("/locations")
    public List<LocationResponse> findAll() {
        return locationService.findAll();
    }

    @PostMapping("/locations")
    public LocationResponse createClassification(@Valid @RequestBody AddLocationRequest addLocationRequest) {
        return locationService.createLocation(addLocationRequest);
    }

    @DeleteMapping("/locations")
    public List<Long> deleteLocations(@RequestBody List<Long> ids) {
        return locationService.deleteLocations(ids);
    }

    @GetMapping("/locations/warehouses")
    List<String> findAllWarehouse(){
        return locationService.findAllWarehouse();
    }

    @GetMapping("/locations/racks/warehouse")
    List<String> findAllWarehouse(@RequestParam String warehouse){
        return locationService.findAllRackByWarehouse(warehouse);
    }

    @GetMapping("/locations/trays/warehouse-rack")
    List<String> findAllTrayByWarehouseAndRack(@RequestParam String warehouse, @RequestParam String rack){
        return locationService.findAllTrayByWarehouseAndRack(warehouse, rack);
    }

    @GetMapping("/locations/export-excel")
    public void exportExcelLocations(@RequestParam List<Long> ids) {
        locationService.exportExcelLocations(ids);
    }
}
