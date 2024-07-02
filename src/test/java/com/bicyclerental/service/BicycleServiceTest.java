package com.bicyclerental.service;

import com.bicyclerental.Bicycle;
import com.bicyclerental.exceptions.ResourceNotFoundException;
import com.bicyclerental.repository.BicycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BicycleServiceTest {

    @Mock
    private BicycleRepository bicycleRepository;

    @InjectMocks
    private BicycleService bicycleService;

    Bicycle bicycle;
    String testModel;
    String testColor;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException  {
         bicycle = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);

    }

    @Test
    public void testGetBicycleById() {
        when(bicycleRepository.findById(1L)).thenReturn(Optional.of(bicycle));
        Bicycle foundBicycle = bicycleService.getBicycleById(1L);
        assertNotNull(foundBicycle);
    }

    @Test
    public void testGetAllBicycles() {
        Bicycle bicycle2 = new Bicycle();
        bicycle2.setModel("Bicycle");
        bicycle2.setColor("blue");

        List<Bicycle> bicycles = new LinkedList<>();
        bicycles.add(bicycle);
        bicycles.add(bicycle2);
        when(bicycleRepository.findAll()).thenReturn(bicycles);

        List<Bicycle> foundBicycles = bicycleService.getAllBicycles();
        assertNotNull(foundBicycles);
        assertEquals(2, foundBicycles.size());
        Iterator<Bicycle> bicyclesIterator = bicycles.iterator();
        Iterator<Bicycle> foundBicyclesIterator = foundBicycles.iterator();

        while (bicyclesIterator.hasNext() && foundBicyclesIterator.hasNext()) {
            assertEquals(bicyclesIterator.next(), foundBicyclesIterator.next());
        }
    }

    @Test
    public void testAddBicycle() {
        when(bicycleRepository.save(bicycle)).thenReturn(bicycle);

        Bicycle savedBicycle = bicycleService.addBicycle(bicycle);
        assertNotNull(savedBicycle);
        assertEquals(bicycle, savedBicycle);
    }

    @Test
    public void testDeleteBicycle() {
        doNothing().when(bicycleRepository).deleteById(1L);

        bicycleService.deleteBicycle(1L);
        verify(bicycleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateBicycle() {
        when(bicycleRepository.findById(1L)).thenReturn(Optional.of(bicycle));
        when(bicycleRepository.save(any(Bicycle.class))).thenReturn(bicycle);

        // When updating with null parameters
        testModel = "testModel";
        bicycle.setModel(testModel);
        testColor = "testColor";
        bicycle.setColor(testColor);
        Bicycle updatedEmptyBicycle = bicycleService.updateBicycle(1L, null, null);
        assertNotNull(updatedEmptyBicycle);
        assertEquals(testModel, updatedEmptyBicycle.getModel());
        assertEquals(testColor, updatedEmptyBicycle.getColor());

        // When updating with non null parameters
        Bicycle updatedBicycle =
                bicycleService.updateBicycle(1L, "New Model", "blue");
        assertNotNull(updatedBicycle);
        assertEquals("New Model", updatedBicycle.getModel());
        assertEquals("blue", updatedBicycle.getColor());
    }

    @Test
    public void testUpdateBicycleNotFound() {
        when(bicycleRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bicycleService.updateBicycle(2L, "New Model", "blue"));
    }
}