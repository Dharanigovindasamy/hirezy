package com.ideas2it.hirezy.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ideas2it.hirezy.dto.JobCategoryDto;

/**
 *<p>
 * Interface for JobCategoryService  to handle  JobCategory-related operation.
 *</p>
 * @author  Kishore
 *
 */
@Component
public interface JobCategoryService {

    /**
     * Retrieves all JobCategory.
     *
     * @return a list of JobCategory entities
     */
    List<JobCategoryDto> getAllJobCategories();

    /**
     * Retrieves an jobCategory by their ID.
     *
     * @param id the ID of the JobCategory to be retrieved
     * @return the jobcategory entity with the specified ID
     */
    JobCategoryDto getJobCategoryById(Long id);

    /**
     * Adds a new JobCategory .
     *
     * @param jobCategoryDto {@link JobCategoryDto} the employee entity to be added
     * @return the saved JobCategory entity
     */
    JobCategoryDto createJobCategory(JobCategoryDto jobCategoryDto);


    /**
     * Updates an existing JobCategory.
     *
     * @param jobCategoryDto {@link JobCategoryDto} the employee entity with updated information
     * @return the updated employee entity
     */
    JobCategoryDto updateJobCategory(Long id, JobCategoryDto jobCategoryDto);

    /**
     * Deletes a JobCategory.
     *
     * @param id the ID of the JobCategory to be deleted
     */
    void deleteJobCategory(Long id);
}
