package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.JobCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing JobCategory entities.
 * Provides CRUD operations for JobCategory entities.
 * This interface extends CrudRepository to inherit basic CRUD methods.
 * @Author  kishore
 */
@Repository
public interface JobCategoryRepository extends CrudRepository<JobCategory, Long> {
    boolean existsByName(String name);
}
