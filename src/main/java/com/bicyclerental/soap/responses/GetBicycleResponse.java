package com.bicyclerental.soap.responses;

import lombok.Data;

@Data
public class GetBicycleResponse {
    private String model;
    private String color;
    private boolean available;
}
