package com.bicyclerental;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class BicycleStationTest {
    BicycleStation bicycleStation = new BicycleStation();

    @Test
    void testBicycleStationId() throws NoSuchFieldException, IllegalAccessException  {
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);
        assertEquals(id, bicycleStation.getId());
    }

    @Test
    void testBicycleStationName() {
        String name = "Bicycle Rental I";
        bicycleStation.setName(name);
        assertEquals(name, bicycleStation.getName());
    }

    @Test
    void testBicycleStationAddress() {
        String address = "al. Politechniki 1";
        bicycleStation.setAddress(address);
        assertEquals(address, bicycleStation.getAddress());
    }

    @Test
    void testBicycleStationBicycles() {

        assertEquals(0, bicycleStation.getBicycles().size());

        LinkedList<Bicycle> bicycleList = new LinkedList<>();

        Bicycle bicycle1 = new Bicycle();
        Bicycle bicycle2 = new Bicycle();
        Bicycle bicycle3 = new Bicycle();

        bicycleList.add(bicycle1);

        bicycleStation.setBicycles(bicycleList);
        assertEquals(1, bicycleStation.getBicycles().size());

        bicycleStation.addBicycle(bicycle2);
        assertEquals(2, bicycleStation.getBicycles().size());
        bicycleStation.removeBicycle(bicycle3);
        assertEquals(2, bicycleStation.getBicycles().size());
        bicycleStation.removeBicycle(bicycle2);
        assertEquals(1, bicycleStation.getBicycles().size());
    }
}
