package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.service.LocationService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * This class handles the functionalities based on location entity
 * REST controller for managing location entities.
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Controller
@RequestMapping("admin/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    private static final Logger logger = LogManager.getLogger();

    /**
     * <p>
     *     Adding new location into the entity
     * </p>
     *
     * @param locationDto - {@link LocationDto} location fields of the location
     * @return LocationDto - locationDto of the location
     */
    @PostMapping
    public ResponseEntity<LocationDto> addLocation (@Valid @RequestBody LocationDto locationDto) {
        locationDto = locationService.addLocation(locationDto);
        logger.info("Location added successfully");
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     *     Display the list of location exists
     * </p>
     *
     * @return List<LocationDto> - list of locations present
     */
    @GetMapping
    public ResponseEntity<List<LocationDto>> displayLocation() {
        List<LocationDto> locationDtos = locationService.displayLocation();
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }

    /**
     * <p>
     *     Display location by giving location id
     * </p>
     *
     * @param id - location Id of the location
     * @return LocationDto - location Dto of the location
     */
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> displayLocationById(@PathVariable("id")Long id) {
        LocationDto locationDto = locationService.displayLocationById(id);
        logger.info("Location displayed by id {}", id);
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Update the location details
     * </p>
     *
     * @param locationDto - {@link LocationDto} given from user
     * @return LocationDto - updated locationDto sent to user
     */
    @PutMapping
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto) {
        locationDto = locationService.updateLocation(locationDto);
        logger.info("Location can be updated by id {}", locationDto.getId());
        return  new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Deleting the location which is available in the list
     * </p>
     *
     * @param id - locationId of the location
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("id")Long id) {
        locationService.deleteLocation(id);
        logger.info("Location can be deleted successfully {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
