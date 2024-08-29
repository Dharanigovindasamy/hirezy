package com.ideas2it.hirezy.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;

/**
 *<p>
 * Interface for JobSubCategoryService  to handle  JobSubCategory-related operation.
 *</p>
 * @author  Kishore
 *
 */
@Component
public interface JobSubCategoryService {
    /**
     * Retrieves all Job subCategory.
     *
     * @return a list of Job subCategory entities
     */
    List<JobSubCategoryDto> getAllJobSubCategories();

    /**
     * Retrieves an job subCategory by their ID.
     *
     * @param id the ID of the JobCategory to be retrieved
     * @return the job subcategory entity with the specified ID
     */
    JobSubCategoryDto getJobSubCategoryById(Long id);

    /**
     * Adds a new JobSubCategory .
     *
     * @param jobSubCategoryDto {@link JobCategoryDto} the employee entity to be added
     * @return the saved JobSubCategory entity
     */
    public JobSubCategoryDto createJobSubcategory(JobSubCategoryDto jobSubCategoryDto);


    /**
     * Updates an existing JobSubCategory.
     *
     * @param jobSubCategoryDto {@link JobSubCategoryDto} the employee entity with updated information
     * @return the updated employee entity
     */
    public JobSubCategoryDto updateJobSubcategory(Long id, JobSubCategoryDto jobSubCategoryDto);

    /**
     * Deletes a JobSubCategory.
     *
     * @param id the ID of the JobSubCategory to be deleted
     */
    void deleteJobSubcategory(Long id);
}
