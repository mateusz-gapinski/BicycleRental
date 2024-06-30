package com.bicyclerental.controller;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.BicycleStationDTOLong;
import com.bicyclerental.dtos.mappers.BicycleStationMapper;
import com.bicyclerental.service.BicycleService;
import com.bicyclerental.service.BicycleStationService;
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
@RequestMapping("/bicyclestations")
public class BicycleStationController {

    private final BicycleStationService bicycleStationService;
    private final BicycleStationMapper bicycleStationMapper;

    @Autowired
    public BicycleStationController(
            BicycleStationService bicycleStationService,
            BicycleStationMapper bicycleStationMapper) {

        this.bicycleStationService = bicycleStationService;
        this.bicycleStationMapper = bicycleStationMapper;
    }

    @GetMapping
    public List<BicycleStationDTOLong> getAllBicycleStations() {
        return bicycleStationService
                .getAllBicycleStations()
                .stream()
                .map(bicycleStationMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BicycleStationDTOLong createBicycleStation(
            @RequestBody BicycleStationDTOLong bicycleStationDTOLong) {
        BicycleStation bicycleStation = bicycleStationMapper.toEntity(bicycleStationDTOLong);
        bicycleStationService.addBicycleStation(bicycleStation);
        return  bicycleStationMapper.toDto(bicycleStation);
    }

    @GetMapping("/{id}")
    public BicycleStationDTOLong getBicycleStationById(@PathVariable Long id) {
        return bicycleStationMapper.toDto(bicycleStationService.getBicycleStationById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bicycleStationService.deleteBicycleStation(id);
    }

    @PostMapping("/{stationId}/rent/{bicycleId}")
    public BicycleDTO rentBicycle(@PathVariable Long stationId, @PathVariable Long bicycleId) {
        Bicycle bicycle = bicycleStationService.rentBicycle(bicycleId, stationId);
        return  bicycleStationMapper.toBicycleDTO(bicycle);
    }

    @PostMapping("/{stationId}/return/{bicycleId}")
    public BicycleDTO returnBicycle(@PathVariable Long stationId, @PathVariable Long bicycleId) {
        Bicycle bicycle = bicycleStationService.returnBicycle(bicycleId, stationId);
        return  bicycleStationMapper.toBicycleDTO(bicycle);
    }
}
