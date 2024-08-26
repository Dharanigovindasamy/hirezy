package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.model.Location;

/**
 * Mapper for converting between LocationPost entity and LocationDTO.
 * @Author  kishore
 */
public class LocationMapper {
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
