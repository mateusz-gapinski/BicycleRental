package com.bicyclerental.service;

import com.bicyclerental.Bicycle;
import com.bicyclerental.exceptions.InvalidOperationException;
import com.bicyclerental.exceptions.ResourceNotFoundException;
import com.bicyclerental.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Bicycle updateBicycle(Long id, String model, String color) {
        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(id);
        if (bicycleOptional.isPresent()) {
            Bicycle bicycle = bicycleOptional.get();
            if (model != null) {
                bicycle.setModel(model);
            }
            if (color != null) {
                bicycle.setColor(color);
            }
            return bicycle;
        } else {
            throw new ResourceNotFoundException("Bicycle with id:" + id + "doesn't exist!");
        }
    }
}
