package com.bicyclerental.service;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.repository.BicycleRepository;
import com.bicyclerental.repository.BicycleStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicycleStationService {
    private BicycleStationRepository bicycleStationRepository;
    private BicycleRepository bicycleRepository;

    @Autowired
    public BicycleStationService(
            BicycleStationRepository bicycleStationRepository,
            BicycleRepository bicycleRepository) {

        this.bicycleStationRepository = bicycleStationRepository;
        this.bicycleRepository = bicycleRepository;
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

    public Bicycle rentBicycle(Long bicycleId, Long bicycleStationId) {

        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(bicycleId);
        Optional<BicycleStation> bicycleStationOptional =
                bicycleStationRepository.findById(bicycleStationId);

        if (bicycleOptional.isPresent() && bicycleStationOptional.isPresent()) {

            Bicycle bicycle = bicycleOptional.get();
            BicycleStation bicycleStation = bicycleStationOptional.get();

            if (bicycle.getOwningStation().getId() == bicycleStation.getId()) {

                if (bicycle.isAvailable()) {
                    bicycle.setAvailable(false);
                    bicycleRepository.save(bicycle);
                    return bicycle;
                } else {
                    throw new IllegalStateException("Bicycle is not available for rent");
                }
            } else {
                throw new IllegalStateException("BicycleStation doesn't have this bicycle");
            }
        } else {
            throw new IllegalArgumentException("Bicycle or BicycleStation not found");
        }
    }

    public Bicycle returnBicycle(Long bicycleId, Long bicycleStationId) {

        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(bicycleId);
        Optional<BicycleStation> bicycleStationOptional =
                bicycleStationRepository.findById(bicycleStationId);

        if (bicycleOptional.isPresent() && bicycleStationOptional.isPresent()) {

            Bicycle bicycle = bicycleOptional.get();
            BicycleStation bicycleStation = bicycleStationOptional.get();

            if (bicycle.getOwningStation().getId() == bicycleStation.getId()) {

                if (!bicycle.isAvailable()) {
                    bicycle.setAvailable(true);
                    bicycleRepository.save(bicycle);
                    return bicycle;
                } else {
                    throw new IllegalStateException("Bicycle is already available for rent");
                }
            } else {
                throw new IllegalStateException("BicycleStation doesn't have this bicycle");
            }
        } else {
            throw new IllegalArgumentException("Bicycle or BicycleStation not found");
        }
    }
}