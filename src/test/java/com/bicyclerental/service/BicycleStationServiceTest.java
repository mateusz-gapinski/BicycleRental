package com.bicyclerental.service;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.exceptions.InvalidOperationException;
import com.bicyclerental.exceptions.ResourceNotFoundException;
import com.bicyclerental.repository.BicycleRepository;
import com.bicyclerental.repository.BicycleStationRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BicycleStationServiceTest {
    @Mock
    private BicycleStationRepository bicycleStationRepository;
    @Mock
    private BicycleRepository bicycleRepository;

    @InjectMocks
    private BicycleStationService bicycleStationService;

    private BicycleStation bicycleStation;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);
    }

    @Test
    public void testFindById() {
        when(bicycleStationRepository.findById(1L)).thenReturn(Optional.of(bicycleStation));
        BicycleStation foundBicycleStation = bicycleStationService.getBicycleStationById(1L);
        assertNotNull(foundBicycleStation);
        assertEquals(bicycleStation, foundBicycleStation);
    }

    @Test
    public void testGetAllBicycleStations() {
        BicycleStation bicycleStation2 = new BicycleStation();
        bicycleStation2.setName("BicycleStation");
        bicycleStation2.setAddress("ul. Niebieska 1");

        List<BicycleStation> bicycleStations = new LinkedList<>();
        bicycleStations.add(bicycleStation);
        bicycleStations.add(bicycleStation2);
        when(bicycleStationRepository.findAll()).thenReturn(bicycleStations);

        List<BicycleStation> foundBicycleStations = bicycleStationService.getAllBicycleStations();
        assertNotNull(foundBicycleStations);
        assertEquals(2, foundBicycleStations.size());
        Iterator<BicycleStation> bicycleStationsIterator = bicycleStations.iterator();
        Iterator<BicycleStation> foundBicycleStationsIterator = foundBicycleStations.iterator();

        while (bicycleStationsIterator.hasNext() && foundBicycleStationsIterator.hasNext()) {
            assertEquals(bicycleStationsIterator.next(), foundBicycleStationsIterator.next());
        }
    }

    @Test
    public void testAddBicycleStation() {
        when(bicycleStationRepository.save(bicycleStation)).thenReturn(bicycleStation);

        BicycleStation savedBicycleStation = bicycleStationService.addBicycleStation(bicycleStation);
        assertNotNull(savedBicycleStation);
        assertEquals(bicycleStation, savedBicycleStation);
    }

    @Test
    public void testDeleteBicycleStation() {
        doNothing().when(bicycleStationRepository).deleteById(1L);

        bicycleStationService.deleteBicycleStation(1L);
        verify(bicycleStationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRentBicycle() throws NoSuchFieldException, IllegalAccessException {
        Bicycle bicycle = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        bicycle.setOwningStation(bicycleStation);

        when(bicycleRepository.findById(1L)).thenReturn(Optional.of(bicycle));
        when(bicycleStationRepository.findById(1L)).thenReturn(Optional.of(bicycleStation));

        // Is at the sart available
        assertEquals(true, bicycle.isAvailable());

        // Normal renting
        Bicycle rentedBicycle = bicycleStationService.rentBicycle(1L, 1L);
        assertEquals(false, bicycle.isAvailable());

        // Renting after already rented
        assertThrows(InvalidOperationException.class,
                () -> bicycleStationService.rentBicycle(1L, 1L));
        assertEquals(false, bicycle.isAvailable());

        // Trying to rent not existing bicycle
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.rentBicycle(2L, 1L));

        // Trying to rent not existing bicycle station
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.rentBicycle(1L, 2L));

        // Trying to rent bicycle in not owning it bicycleStation
        BicycleStation bicycleStation2 = new BicycleStation();
        when(bicycleStationRepository.findById(bicycleStation2.getId()))
                .thenReturn(Optional.of(bicycleStation2));
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.rentBicycle(1L, bicycleStation2.getId()));
    }

    @Test
    public void testReturnBicycle() throws NoSuchFieldException, IllegalAccessException {
        Bicycle bicycle = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        bicycle.setOwningStation(bicycleStation);

        when(bicycleRepository.findById(1L)).thenReturn(Optional.of(bicycle));
        when(bicycleStationRepository.findById(1L)).thenReturn(Optional.of(bicycleStation));

        // Is at the sart available
        assertEquals(true, bicycle.isAvailable());

        // Normal renting
        if (bicycle.isAvailable()) {
            Bicycle rentedBicycle = bicycleStationService.rentBicycle(1L, 1L);
        }
        Bicycle rentedBicycle = bicycleStationService.returnBicycle(1L, 1L);
        assertEquals(true, bicycle.isAvailable());

        // Renting after already rented
        assertThrows(InvalidOperationException.class,
                () -> bicycleStationService.returnBicycle(1L, 1L));
        assertEquals(true, bicycle.isAvailable());

        // Trying to rent not existing bicycle
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.returnBicycle(2L, 1L));

        // Trying to rent not existing bicycle station
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.returnBicycle(1L, 2L));

        // Trying to rent bicycle in not owning it bicycleStation
        BicycleStation bicycleStation2 = new BicycleStation();
        when(bicycleStationRepository.findById(bicycleStation2.getId()))
                .thenReturn(Optional.of(bicycleStation2));
        assertThrows(ResourceNotFoundException.class,
                () -> bicycleStationService.returnBicycle(1L, bicycleStation2.getId()));
    }
}
