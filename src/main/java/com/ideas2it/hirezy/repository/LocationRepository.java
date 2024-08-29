package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *     This class used for storing the location entity data into the table and perform CRUD operations
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Repository
public interface LocationRepository extends CrudRepository<Location ,Long> {

    /**
     * Retrieve list of locations which are in the list
     * @return list of locations
     */
    List<Location> findByIsDeletedFalse();

    /**
     * Find location by giving state and city
     * @param state - state given by user
     * @param city - city given by the user
     * @return Location - location found under the state and city
     */
    Optional<Location> findByStateAndCity(String state, String city);
}
