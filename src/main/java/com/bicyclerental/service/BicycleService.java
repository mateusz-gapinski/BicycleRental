package com.bicyclerental.service;

import com.bicyclerental.Bicycle;
import com.bicyclerental.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleService {
    private BicycleRepository bicycleRepository;

    @Autowired
    public BicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    public List<Bicycle> getAllBicycles() {
        return bicycleRepository.findAll();
    }

    public Bicycle getBicycleById(Long id) {
        return bicycleRepository.findById(id).orElse(null);
    }

    public Bicycle addBicycle(Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }

    public void deleteBicycle(Long id) {
        bicycleRepository.deleteById(id);
    }
}
