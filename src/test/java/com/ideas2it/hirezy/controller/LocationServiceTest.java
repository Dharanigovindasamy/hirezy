package com.ideas2it.hirezy.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.LocationRepository;
import com.ideas2it.hirezy.service.LocationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    Location location;
    LocationDto locationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = Location.builder()
                .state("tamilnadu")
                .city("chennai").build();
        locationDto = LocationDto.builder()
                .state("tamilnadu")
                .city("chennai").build();
    }

    @Test
    void testAddLocation() {
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        LocationDto response = locationService.addLocation(locationDto);
        assertNotNull(response);
        assertEquals(1,response.getId());
    }

    @Test
    void testDisplayLocation() {
        when(locationRepository.findByIsDeletedFalse()).thenReturn(List.of(location));
        List<LocationDto> response = locationService.displayLocation();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testDisplayLocationById() {
        when(locationRepository.findById(locationDto.getId())).thenReturn(Optional.ofNullable(location));
        LocationDto response = locationService.displayLocationById(locationDto.getId());
        assertNotNull(response);
        assertEquals(locationDto.getCity(),response.getCity());
    }

    @Test
    void testDisplayLocationFailure() {
        when(locationRepository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> locationService.displayLocationById(3L));
    }
    @Test
    void testUpdateLocation() {
        when(locationRepository.findById(locationDto.getId())).thenReturn(Optional.of(location));
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        LocationDto response = locationService.updateLocation(locationDto);
        assertNotNull(response);
        assertEquals(locationDto.getId(), response.getId());
    }

    @Test
    void testUpdateLocationFailures() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> locationService.updateLocation(locationDto));
    }


    @Test
    void testDeleteLocation() {
        when(locationRepository.findById(locationDto.getId())).thenReturn(Optional.ofNullable(location));
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        locationService.deleteLocation(locationDto.getId());
        assertEquals(true, location.isDeleted());
    }

    @Test
    void testDeleteLocationFailure() {
        when(locationRepository.findById(locationDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> locationService.deleteLocation(locationDto.getId()));
    }

    @Test
    void testFindOrCreateLocations() {
        when(locationRepository.findByStateAndCity(locationDto.getState(), locationDto.getCity()))
                .thenReturn(Optional.ofNullable(location));
        Location response = locationService.findOrCreateLocation(locationDto.getState(), locationDto.getCity());
        assertNotNull(response);
        assertEquals(locationDto.getCity(), response.getCity());
    }


}
