package com.ideas2it.hirezy.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ideas2it.hirezy.service.EmployerServiceImpl.logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.service.LocationService;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    Location location;
    LocationDto locationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = Location.builder()
                .id(1L)
                .state("tamilnadu")
                .city("chennai").build();
        locationDto = LocationDto.builder()
                .id(1L)
                .state("tamilnadu")
                .city("chennai").build();
    }

    @Test
    void testAddLocation() {
        when(locationService.addLocation(locationDto)).thenReturn(locationDto);
        ResponseEntity<LocationDto> response = locationController.addLocation(locationDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(locationDto.getCity(), response.getBody().getCity());
    }

    @Test
    void testDisplayLocations() {
        when(locationService.displayLocation()).thenReturn(List.of(locationDto));
        ResponseEntity<List<LocationDto>> response = locationController.displayLocation();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testUpdateLocation() {
        when(locationService.updateLocation(locationDto)).thenReturn(locationDto);
        ResponseEntity<LocationDto> response = locationController.updateLocation(locationDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(locationDto.getCity(), response.getBody().getCity());
    }

    @Test
    void testDeleteLocation() {
        doNothing().when(locationService).deleteLocation(locationDto.getId());
        ResponseEntity<Void> response = locationController.deleteLocation(locationDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDisplayLocationById() {
        when(locationService.displayLocationById(anyLong())).thenReturn(locationDto);
        ResponseEntity<LocationDto> response = locationController.displayLocationById(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locationDto, response.getBody());
        verify(locationService).displayLocationById(anyLong());
    }
}
