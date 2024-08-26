package com.ideas2it.hirezy.repository;


import com.ideas2it.hirezy.model.JobSubCategory;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing JobSubCategory entities.
 * Provides CRUD operations for JobSubCategory entities.
 * This interface extends CrudRepository to inherit basic CRUD methods.
 * @Author  kishore
 */
public interface JobSubCategoryRepository extends CrudRepository <JobSubCategory, Long>  {
}
