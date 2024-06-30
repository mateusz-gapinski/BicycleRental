package com.bicyclerental.dtos.mappers;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.BicycleStationDTOLong;
import com.bicyclerental.service.BicycleStationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BicycleStationMapper {
    @Autowired
    BicycleStationService bicycleStationService = null;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "bicycles", target = "bicycles", qualifiedByName = "toBicycleDTOs")
    public abstract BicycleStationDTOLong toDto(BicycleStation bicycleStation);

    //@Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    //@Mapping(source = "bicycles", target = "bicycles", qualifiedByName = "toBicycles")
    public abstract BicycleStation toEntity(BicycleStationDTOLong bicycleStationDTOLong);

    @Named("toBicycleDTOs")
    public List<BicycleDTO> toBicycleDTOs(List<Bicycle> bicycles) {
        return bicycles.stream()
                .map(this::toBicycleDTO)
                .collect(Collectors.toList());
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "owningStation",
            target = "owningStation",
            qualifiedByName = "extractBicycleStationId")
    public abstract BicycleDTO toBicycleDTO(Bicycle address);

    @Named("toBicycles")
    public List<Bicycle> toBicycles(List<BicycleDTO> bicycleDTOs) {
        return bicycleDTOs.stream()
                .map(this::toBicycle)
                .collect(Collectors.toList());
    }

    //@Mapping(source = "id", target = "id")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "owningStation",
            target = "owningStation",
            qualifiedByName = "getBicycleStationById")
    public abstract Bicycle toBicycle(BicycleDTO bicycleDTO);


    @Named("extractBicycleStationId")
    public Long extractBicycleStationId(BicycleStation bicycleStation) {
        return bicycleStation.getId();
    }

    @Named("getBicycleStationById")
    public BicycleStation getBicycleStationById(Long id) {
        return bicycleStationService.getBicycleStationById(id);
    }
}
