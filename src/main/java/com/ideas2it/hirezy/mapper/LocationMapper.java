package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.model.Location;

/**
 * Mapper for converting between LocationPost entity and LocationDTO.
 * @author  kishore
 */
public class LocationMapper {

    /**
     * This method used for conversion of location to locationDto
     * @param location - location entity of the user
     * @return LocationDto -{@link LocationDto} given to the user
     */
    public static LocationDto mapToLocationDto(Location location) {
        if (location == null) {
            return null;
        }

        return LocationDto.builder()
                .id(location.getId())
                .state(location.getState())
                .city(location.getCity())
                .build();
    }

    /**
     * <p>
     *     This method used for conversion of Dto to entity
     * </p>
     * @param locationDto -{@link LocationDto} given from the user
     * @return location - location entity of the user
     */
    public static Location mapToLocation(LocationDto locationDto) {
        if (locationDto == null) {
            return null;
        }

        return Location.builder()
                .id(locationDto.getId())
                .state(locationDto.getState())
                .city(locationDto.getCity())
                .build();
    }
}
