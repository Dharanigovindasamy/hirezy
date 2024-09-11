package com.ideas2it.hirezy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.JobCategory;

import java.util.Optional;

/**
 * Repository interface for managing JobCategory entities.
 * Provides CRUD operations for JobCategory entities.
 * This interface extends CrudRepository to inherit basic CRUD methods.
 * @Author  kishore
 */
@Repository
public interface JobCategoryRepository extends CrudRepository<JobCategory, Long> {

    /**
     * Retrieve the job category name
     *
     * @param name - name of the job category
     * @return true if searching category is present or else false
     */
    boolean existsByName(String name);

    /**
     *  Retrieve by jobCategory id
     * @param id Jobcategory id
     * @return Active jobcategory.
     */
    Optional<JobCategory> findByIdAndIsDeletedFalse(Long id);
}

