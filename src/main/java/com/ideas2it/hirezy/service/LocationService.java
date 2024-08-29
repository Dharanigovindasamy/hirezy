package com.ideas2it.hirezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.model.Location;

/**
 *<p>
 *  This interface used for performing CRUD operations in location object
 *</p>>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public interface LocationService {

    /**
     * <p>
     *     Adding location details
     * </p>
     *
     * @param locationDto - locationDto given from user
     * @return locationDto - locationDto after added into the table
     */
    LocationDto addLocation(LocationDto locationDto);

    /**
     * <p>
     *     Display all locations in the list
     * </p>
     *
     * @return List<LocationDto> - list of locations
     */
    List<LocationDto> displayLocation();

    /**
     * <p>
     *     Display locations by giving location id
     * </p>
     *
     * @param id - location id of the location
     * @return locationDto - displaying locationDto of respective id
     */
    LocationDto displayLocationById(Long id);

    /**
     * <p>
     *     Updating location by location Dto
     * </p>
     *
     * @param locationDto - locationDto from user
     * @return locationDto -after updating locationDto sent to user
     */
    LocationDto updateLocation(LocationDto locationDto);

    /**
     * <p>
     *     Deleting location by location id
     * </p>
     *
     * @param id - location id of the location
     */
    void deleteLocation(Long id);

    /**
     * <p>
     * Finds an existing location based on the provided state and city, or creates a new location if none exists.
     * </p>
     *
     * @param state The state where the location is situated.
     * @param city The city where the location is situated.
     * @return The Location object, either retrieved from or newly created in the database.
     */
    Location findOrCreateLocation(String state, String city);
}
