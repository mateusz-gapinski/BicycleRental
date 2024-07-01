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
        String name = "Central Park Station";
        station.setName(name);
        assertEquals(name, station.getName());
    }

    @Test
    public void testAddress() {
        BicycleStationDTOLong station = new BicycleStationDTOLong();
        String address = "123 Main St, New York, NY";
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
