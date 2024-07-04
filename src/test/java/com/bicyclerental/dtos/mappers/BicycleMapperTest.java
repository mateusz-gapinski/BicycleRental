package com.bicyclerental.dtos.mappers;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.BicycleStationTest;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.service.BicycleStationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class BicycleMapperTest {

    @Mock
    private BicycleStationService bicycleStationService;


    private BicycleMapper bicycleMapper;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        bicycleMapper = new BicycleMapperImpl();
        Field bssField = BicycleMapper.class.getDeclaredField("bicycleStationService");
        bssField.setAccessible(true);
        bssField.set(bicycleMapper, bicycleStationService);
    }

    @Test
    public void testExtractId() throws NoSuchFieldException, IllegalAccessException {
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        assertEquals(id, bicycleMapper.extractId(bicycleStation));
    }

    @Test
    public void testGetById() throws NoSuchFieldException, IllegalAccessException {
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        when(bicycleStationService.getBicycleStationById(id)).thenReturn(bicycleStation);

        //BicycleStation gotBicycleStation = bicycleStationService.getBicycleStationById(id);
        BicycleStation gotBicycleStation = bicycleMapper.getById(id);

        assertEquals(bicycleStation, gotBicycleStation);
    }

    @Test
    public void testToDTO() throws NoSuchFieldException, IllegalAccessException {
        Bicycle bicycle = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        String model = "model";
        String color = "color";
        boolean available = true;
        BicycleStation bicycleStation = new BicycleStation();

        bicycle.setModel(model);
        bicycle.setColor(color);
        bicycle.setAvailable(available);
        bicycle.setOwningStation(bicycleStation);

        BicycleDTO bicycleDTO = bicycleMapper.toDto(bicycle);

        assertEquals(id, bicycleDTO.getId());
        assertEquals(model, bicycleDTO.getModel());
        assertEquals(color, bicycleDTO.getColor());
        assertEquals(available, bicycleDTO.isAvailable());
        assertEquals(bicycleStation.getId(), bicycleDTO.getOwningStation());

        assertNull(bicycleMapper.toDto(null));
    }

    @Test
    public void testToEntity() throws NoSuchFieldException, IllegalAccessException {
        String model = "model";
        String color = "color";
        boolean available = true;
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        BicycleDTO bicycleDTO = new BicycleDTO();
        bicycleDTO.setModel(model);
        bicycleDTO.setColor(color);
        bicycleDTO.setAvailable(available);
        bicycleDTO.setOwningStation(bicycleStation.getId());

        when(bicycleMapper.getById(id)).thenReturn(bicycleStation);

        Bicycle bicycle = bicycleMapper.toEntity(bicycleDTO);

        assertEquals(model, bicycle.getModel());
        assertEquals(color, bicycle.getColor());
        assertEquals(available, bicycle.isAvailable());
        assertEquals(bicycleStation, bicycle.getOwningStation());

        assertNull(bicycleMapper.toEntity(null));
    }
}
