package com.bicyclerental.dtos;

import com.bicyclerental.dtos.BicycleDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BicycleDTOTest {

    @Test
    public void testId() {
        BicycleDTO bicycleDTO = new BicycleDTO();
        Long id = 1L;
        bicycleDTO.setId(id);
        assertEquals(id, bicycleDTO.getId());
    }

    @Test
    public void testModel() {
        BicycleDTO bicycleDTO = new BicycleDTO();
        String model = "Mountain Bike";
        bicycleDTO.setModel(model);
        assertEquals(model, bicycleDTO.getModel());
    }

    @Test
    public void testColor() {
        BicycleDTO bicycleDTO = new BicycleDTO();
        String color = "Red";
        bicycleDTO.setColor(color);
        assertEquals(color, bicycleDTO.getColor());
    }

    @Test
    public void testAvailable() {
        BicycleDTO bicycleDTO = new BicycleDTO();
        boolean available = true;
        bicycleDTO.setAvailable(available);
        assertTrue(bicycleDTO.isAvailable());
    }

    @Test
    public void testOwningStation() {
        BicycleDTO bicycleDTO = new BicycleDTO();
        Long owningStation = 1L;
        bicycleDTO.setOwningStation(owningStation);
        assertEquals(owningStation, bicycleDTO.getOwningStation());
    }
}
