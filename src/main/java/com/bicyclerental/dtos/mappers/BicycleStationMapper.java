package com.bicyclerental.dtos.mappers;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.BicycleStationDTOLong;
import com.bicyclerental.dtos.BicycleStationDTOShort;
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
    public abstract BicycleStation toEntity(BicycleStationDTOShort bicycleStationDTOShort);

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
