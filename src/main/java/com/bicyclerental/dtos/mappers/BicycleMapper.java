package com.bicyclerental.dtos.mappers;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.service.BicycleStationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BicycleMapper {
    @Autowired
    BicycleStationService bicycleStationService = null;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "owningStation",
            target = "owningStation",
            qualifiedByName = "extractId")
    public abstract BicycleDTO toDto(Bicycle bicycle);

    //@Mapping(source = "id", target = "id")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "owningStation",
            target = "owningStation",
            qualifiedByName = "getById")
    public abstract Bicycle toEntity(BicycleDTO bicycleDTO);

    @Named("extractId")
    public Long extractId(BicycleStation bicycleStation) {
        return bicycleStation.getId();
    }

    @Named("getById")
    public BicycleStation getById(Long id) {
        return bicycleStationService.getBicycleStationById(id);
    }
}