package com.bicyclerental.dtos;

import com.bicyclerental.dtos.BicycleStationDTOShort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BicycleStationDTOShortTest {

    @Test
    public void testId() {
        BicycleStationDTOShort station = new BicycleStationDTOShort();
        Long id = 1L;
        station.setId(id);
        assertEquals(id, station.getId());
    }

    @Test
    public void testName() {
        BicycleStationDTOShort station = new BicycleStationDTOShort();
        String name = "Central Park Station";
        station.setName(name);
        assertEquals(name, station.getName());
    }

    @Test
    public void testAddress() {
        BicycleStationDTOShort station = new BicycleStationDTOShort();
        String address = "123 Main St, New York, NY";
        station.setAddress(address);
        assertEquals(address, station.getAddress());
    }
}
