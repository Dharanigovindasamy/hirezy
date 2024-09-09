package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.LocationRepository;
import static com.ideas2it.hirezy.mapper.LocationMapper.mapToLocation;
import static com.ideas2it.hirezy.mapper.LocationMapper.mapToLocationDto;

/**
 * <p>
 *  The class used for performing Crud operations and conversion of dto to entity and viceversa
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private final LocationRepository locationRepository;

    private static final Logger logger = LogManager.getLogger(LocationServiceImpl.class);

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        Location location = mapToLocation(locationDto);
        logger.info("Location added successfully");
        return mapToLocationDto(locationRepository.save(location));
    }

    @Override
    public List<LocationDto> displayLocation() {
        List<LocationDto> locationDtos = new ArrayList<>();
        List<Location> locations = locationRepository.findByIsDeletedFalse();
        for(Location location : locations) {
            LocationDto locationDto = mapToLocationDto(location);
            locationDtos.add(locationDto);
        }
        return locationDtos;
    }

    @Override
    public LocationDto displayLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("location not found" + id));
        return mapToLocationDto(location);
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        if(!locationRepository.existsById(locationDto.getId())) {
            logger.warn("location not found with id {}", locationDto.getId());
            throw new ResourceNotFoundException("location not found" + locationDto.getId());
        }
        Location location = mapToLocation(locationDto);
        Location updatedLocation = locationRepository.save(location);
        logger.info("Location updated successfully {}", locationDto.getId());
        return mapToLocationDto(updatedLocation);
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("location not found" + id));
        if (null == location) {
            logger.warn("No location found {}", id);
        }
        if (location != null) {
            location.setDeleted(true);
            locationRepository.save(location);
        }
        logger.info("Location id deleted successfully {} ", id);
    }

    @Override
    public Location findOrCreateLocation(String state, String city) {
        Optional<Location> locationOptional = locationRepository.findByStateAndCity(state, city);
        if (locationOptional.isPresent()) {
            return locationOptional.get();
        } else {
            Location newLocation = new Location();
            newLocation.setState(state);
            newLocation.setCity(city);
            return locationRepository.save(newLocation);
        }
    }
}
