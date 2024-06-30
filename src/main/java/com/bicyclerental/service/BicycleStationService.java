package com.bicyclerental.service;

import com.bicyclerental.BicycleStation;
import com.bicyclerental.repository.BicycleStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleStationService {
    private BicycleStationRepository bicycleStationRepository;

    @Autowired
    public BicycleStationService(BicycleStationRepository bicycleStationRepository) {
        this.bicycleStationRepository = bicycleStationRepository;
    }

    public List<BicycleStation> getAllBicycleStations() {
        return bicycleStationRepository.findAll();
    }

    public BicycleStation getBicycleStationById(Long id) {
        return bicycleStationRepository.findById(id).orElse(null);
    }

    public BicycleStation addBicycleStation(BicycleStation bicycleStation) {
        return bicycleStationRepository.save(bicycleStation);
    }

    public void deleteBicycleStation(Long id) {
        bicycleStationRepository.deleteById(id);
    }
}