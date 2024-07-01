package com.bicyclerental.dtos;

import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.BicycleStationDTOLong;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

public class BicycleStationDTOLongTest {

    @Test
    public void testId() {
        BicycleStationDTOLong station = new BicycleStationDTOLong();
        Long id = 1L;
        station.setId(id);
        assertEquals(id, station.getId());
    }

    @Test
    public void testName() {
        BicycleStationDTOLong station = new BicycleStationDTOLong();
        String name = "Bicycle Rental II";
        station.setName(name);
        assertEquals(name, station.getName());
    }

    @Test
    public void testAddress() {
        BicycleStationDTOLong station = new BicycleStationDTOLong();
        String address = "al. Politechniki 1";
        station.setAddress(address);
        assertEquals(address, station.getAddress());
    }

    @Test
    public void testBicycles() {
        BicycleStationDTOLong station = new BicycleStationDTOLong();
        LinkedList<BicycleDTO> bicycles = new LinkedList<>();
        BicycleDTO bike = new BicycleDTO();
        bike.setId(1L);
        bicycles.add(bike);
        station.setBicycles(bicycles);
        assertEquals(bicycles, station.getBicycles());
    }
}
