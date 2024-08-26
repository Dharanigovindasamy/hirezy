package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
public interface LocationRepository extends CrudRepository<Location , Long> {
    Optional<Location> findByStateAndCity(String state, String city);
}
