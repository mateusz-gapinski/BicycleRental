package com.bicyclerental.dtos.mappers;

import com.bicyclerental.Bicycle;
import com.bicyclerental.BicycleStation;
import com.bicyclerental.dtos.BicycleDTO;
import com.bicyclerental.dtos.BicycleStationDTOLong;
import com.bicyclerental.dtos.BicycleStationDTOShort;
import com.bicyclerental.service.BicycleStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BicycleStationMapperTest {

    @Mock
    private BicycleStationService bicycleStationService;

    private BicycleStationMapper bicycleStationMapper;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        bicycleStationMapper = new BicycleStationMapperImpl();
        Field bssField = BicycleStationMapper.class.getDeclaredField("bicycleStationService");
        bssField.setAccessible(true);
        bssField.set(bicycleStationMapper, bicycleStationService);
    }

    @Test
    public void testToDto() throws NoSuchFieldException, IllegalAccessException {
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);
        bicycleStation.setName("Station 1");
        bicycleStation.setAddress("123 Main St");

        Bicycle bicycle = new Bicycle();
        idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        bicycle.setModel("Model 1");
        bicycle.setColor("Red");
        bicycle.setAvailable(true);
        bicycle.setOwningStation(bicycleStation);

        List<Bicycle> bicycles = Collections.singletonList(bicycle);
        bicycleStation.setBicycles(bicycles);

        BicycleStationDTOLong bicycleStationDTOLong = bicycleStationMapper.toDto(bicycleStation);

        assertEquals(bicycleStation.getId(), bicycleStationDTOLong.getId());
        assertEquals(bicycleStation.getName(), bicycleStationDTOLong.getName());
        assertEquals(bicycleStation.getAddress(), bicycleStationDTOLong.getAddress());

        assertNotNull(bicycleStationDTOLong.getBicycles());
        assertEquals(1, bicycleStationDTOLong.getBicycles().size());

        BicycleDTO bicycleDTO = bicycleStationDTOLong.getBicycles().get(0);
        assertEquals(bicycle.getId(), bicycleDTO.getId());
        assertEquals(bicycle.getModel(), bicycleDTO.getModel());
        assertEquals(bicycle.getColor(), bicycleDTO.getColor());
        assertEquals(bicycle.isAvailable(), bicycleDTO.isAvailable());

        assertNull(bicycleStationMapper.toDto(null));
    }

    @Test
    public void testToEntity() {
        BicycleStationDTOShort bicycleStationDTOShort = new BicycleStationDTOShort();
        bicycleStationDTOShort.setId(1L);
        bicycleStationDTOShort.setName("Station 1");
        bicycleStationDTOShort.setAddress("123 Main St");

        BicycleStation bicycleStation = bicycleStationMapper.toEntity(bicycleStationDTOShort);

        assertEquals(bicycleStationDTOShort.getName(), bicycleStation.getName());
        assertEquals(bicycleStationDTOShort.getAddress(), bicycleStation.getAddress());

        assertNull(bicycleStationMapper.toEntity(null));
    }

    @Test
    public void testToBicycleDTOs() throws NoSuchFieldException, IllegalAccessException {

        BicycleStation bicycleStation = new BicycleStation();

        Bicycle bicycle1 = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle1, id);
        bicycle1.setModel("Model 1");
        bicycle1.setColor("Color 1");
        bicycle1.setAvailable(true);
        bicycle1.setOwningStation(bicycleStation);

        Bicycle bicycle2 = new Bicycle();
        id = 2L;
        idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle2, id);
        bicycle2.setModel("Model 2");
        bicycle2.setColor("Color 2");
        bicycle2.setAvailable(false);
        bicycle2.setOwningStation(bicycleStation);

        List<Bicycle> bicycles = Arrays.asList(bicycle1, bicycle2);

        List<BicycleDTO> bicycleDTOs = bicycleStationMapper.toBicycleDTOs(bicycles);

        assertNotNull(bicycleDTOs);
        assertEquals(2, bicycleDTOs.size());

        BicycleDTO bicycleDTO1 = bicycleDTOs.get(0);
        assertEquals(bicycle1.getId(), bicycleDTO1.getId());
        assertEquals(bicycle1.getModel(), bicycleDTO1.getModel());
        assertEquals(bicycle1.getColor(), bicycleDTO1.getColor());
        assertEquals(bicycle1.isAvailable(), bicycleDTO1.isAvailable());
        assertEquals(bicycle1.getOwningStation().getId(), bicycleDTO1.getOwningStation());

        BicycleDTO bicycleDTO2 = bicycleDTOs.get(1);
        assertEquals(bicycle2.getId(), bicycleDTO2.getId());
        assertEquals(bicycle2.getModel(), bicycleDTO2.getModel());
        assertEquals(bicycle2.getColor(), bicycleDTO2.getColor());
        assertEquals(bicycle2.isAvailable(), bicycleDTO2.isAvailable());
        assertEquals(bicycle2.getOwningStation().getId(), bicycleDTO2.getOwningStation());

        //assertNull(bicycleStationMapper.toBicycleDTOs(null));
    }

    @Test
    public void testToBicycleDTO() throws NoSuchFieldException, IllegalAccessException {
        Bicycle bicycle = new Bicycle();
        Long id = 1L;
        Field idField = Bicycle.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycle, id);
        bicycle.setModel("Model 1");
        bicycle.setColor("Red");
        bicycle.setAvailable(true);
        bicycle.setOwningStation(new BicycleStation());


        BicycleDTO bicycleDTO = bicycleStationMapper.toBicycleDTO(bicycle);

        assertEquals(bicycle.getId(), bicycleDTO.getId());
        assertEquals(bicycle.getModel(), bicycleDTO.getModel());
        assertEquals(bicycle.getColor(), bicycleDTO.getColor());
        assertEquals(bicycle.isAvailable(), bicycleDTO.isAvailable());
        assertEquals(bicycle.getOwningStation().getId(), bicycleDTO.getOwningStation());

        assertNull(bicycleStationMapper.toBicycleDTO(null));
    }

    @Test
    public void testToBicycle() throws NoSuchFieldException, IllegalAccessException {
        BicycleDTO bicycleDTO = new BicycleDTO();
        bicycleDTO.setId(1L);
        bicycleDTO.setModel("Model 1");
        bicycleDTO.setColor("Red");
        bicycleDTO.setAvailable(true);
        bicycleDTO.setOwningStation(1L);

        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        when(bicycleStationService.getBicycleStationById(1L)).thenReturn(bicycleStation);

        Bicycle bicycle = bicycleStationMapper.toBicycle(bicycleDTO);

        assertAll(
                () -> assertEquals(bicycleDTO.getModel(), bicycle.getModel()),
                () -> assertEquals(bicycleDTO.getColor(), bicycle.getColor()),
                () -> assertEquals(bicycleDTO.isAvailable(), bicycle.isAvailable()),
                () -> assertEquals(bicycleStation, bicycle.getOwningStation()),
                () -> assertNull(bicycleStationMapper.toBicycle(null))
        );
    }

    @Test
    public void testExtractBicycleStationId() throws NoSuchFieldException, IllegalAccessException {
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        id = bicycleStationMapper.extractBicycleStationId(bicycleStation);

        assertEquals(1L, id);
    }

    @Test
    public void testGetBicycleStationById() throws NoSuchFieldException, IllegalAccessException {
        BicycleStation bicycleStation = new BicycleStation();
        Long id = 1L;
        Field idField = BicycleStation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(bicycleStation, id);

        when(bicycleStationService.getBicycleStationById(1L)).thenReturn(bicycleStation);

        BicycleStation result = bicycleStationMapper.getBicycleStationById(1L);

        assertEquals(bicycleStation, result);
    }
}
