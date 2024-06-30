package com.bicyclerental.controller;

import com.bicyclerental.Bicycle;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.mappers.BicycleMapper;
import com.bicyclerental.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;
    private final BicycleMapper bicycleMapper;

    @Autowired
    public BicycleController(BicycleService bicycleService, BicycleMapper bicycleMapper) {
        this.bicycleService = bicycleService;
        this.bicycleMapper = bicycleMapper;
    }

    @GetMapping
    public List<BicycleDTO> getAllBicycles() {
        return bicycleService
                .getAllBicycles()
                .stream()
                .map(bicycleMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BicycleDTO createBicycle(@RequestBody BicycleDTO bicycleDTO) {
        Bicycle bicycle = bicycleMapper.toEntity(bicycleDTO);
        bicycleService.addBicycle(bicycle);
        return bicycleMapper.toDto(bicycle);
    }

    @GetMapping("/{id}")
    public BicycleDTO getBicycleById(@PathVariable Long id) {
        return bicycleMapper.toDto(bicycleService.getBicycleById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteBicycle(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
    }
}
