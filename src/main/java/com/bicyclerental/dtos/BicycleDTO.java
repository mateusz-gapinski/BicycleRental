package com.bicyclerental.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BicycleDTO {
    private Long id;
    private String model;
    private String color;
    private boolean available;
    private Long owningStation;
}
