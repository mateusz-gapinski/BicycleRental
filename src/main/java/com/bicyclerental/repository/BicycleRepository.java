package com.bicyclerental.repository;

import com.bicyclerental.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BicycleRepository extends JpaRepository<Bicycle, Long> {
}
