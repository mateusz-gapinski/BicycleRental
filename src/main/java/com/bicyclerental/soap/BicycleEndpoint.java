package com.bicyclerental.soap;

import com.bicyclerental.Bicycle;
import com.bicyclerental.service.BicycleService;
import com.bicyclerental.soap.requests.GetBicycleRequest;
import com.bicyclerental.soap.responses.GetBicycleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BicycleEndpoint {

    private final BicycleService bicycleService;

    private static final String NAMESPACE_URI = "http://bicyclerental.com/bicycles";

    @Autowired
    public BicycleEndpoint(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBicycleRequest")
    @ResponsePayload
    public GetBicycleResponse getBicycle(@RequestPayload GetBicycleRequest request) {
        GetBicycleResponse response = new GetBicycleResponse();
        Bicycle bicycle = bicycleService.getBicycleById(request.getBicycleId());
        response.setModel(bicycle.getModel());
        response.setColor(bicycle.getColor());
        response.setAvailable(bicycle.isAvailable());
        return response;
    }
}