package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.model.JobSubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

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
     * Retrieves all JobsubCategory.
     *
     * @return a list of JobsubCategory entities
     */
    List<JobSubCategoryDto> getAllJobSubCategories();

    /**
     * Retrieves an jobsubCategory by their ID.
     *
     * @param id the ID of the JobCategory to be retrieved
     * @return the jobsubcategory entity with the specified ID
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
