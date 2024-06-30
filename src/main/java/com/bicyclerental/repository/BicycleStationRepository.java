package com.bicyclerental.repository;

import com.bicyclerental.BicycleStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BicycleStationRepository extends JpaRepository<BicycleStation, Long> {
}
