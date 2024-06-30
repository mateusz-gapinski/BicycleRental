package com.bicyclerental.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BicycleStationDTOLong {
    private Long id;
    private String name;
    private String address;
    private List<BicycleDTO> bicycles = new ArrayList<>();
}
