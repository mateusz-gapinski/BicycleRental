package com.bicyclerental;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

public class BicycleTest {
    Bicycle bicycle = new Bicycle();

    @Test
    void testBicycleId() throws NoSuchFieldException, IllegalAccessException  {
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        assertEquals(id, bicycle.getId());
    }

    @Test
    void testBicycleModel() {
        String model = "Mountain Bike";
        bicycle.setModel(model);
        assertEquals(model, bicycle.getModel());
    }

    @Test
    void testBicycleColor() {
        String color = "Red";
        bicycle.setColor(color);
        assertEquals(color, bicycle.getColor());
    }

    @Test
    void testBicycleAvailable() {
        boolean available = true;
        bicycle.setAvailable(available);
        assertTrue(bicycle.isAvailable());
    }

    @Test
    void testBicycleOwningStation() {
        BicycleStation station = new BicycleStation();
        bicycle.setOwningStation(station);
        assertEquals(station, bicycle.getOwningStation());
    }
}
