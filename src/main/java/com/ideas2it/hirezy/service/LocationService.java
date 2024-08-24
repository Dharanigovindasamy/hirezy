package com.ideas2it.hirezy.service;

import java.util.List;
import com.ideas2it.hirezy.dto.LocationDto;
import org.springframework.stereotype.Service;

/**
 *<p>
 *  This interface used for performing CRUD operations
 *
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
     * @return locationDto - dispalying locationDto of respective id
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
}
