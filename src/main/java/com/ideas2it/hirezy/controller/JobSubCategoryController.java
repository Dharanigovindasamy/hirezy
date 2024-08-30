package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.service.JobSubCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing JobSubCategory entities.
 * Provides endpoints to create, retrieve, update, delete JobCategory.
 */
@RestController
@RequestMapping("/jobsubcategory")
public class JobSubCategoryController {
    @Autowired
    private JobSubCategoryService jobSubCategoryService;

    private static final Logger logger = LogManager.getLogger(JobCategoryController.class);

    /**
     * Creates a new JobSubCategory.
     *
     * @param jobSubCategoryDto {@link JobSubCategoryDto} The DTO containing JobSubCategory data.
     * @return The created JobSubCategory DTO with HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<JobSubCategoryDto> addJobSubCategory(@RequestBody JobSubCategoryDto jobSubCategoryDto) {
        logger.info("Request to create JobSubCategory with details: {}", jobSubCategoryDto);
        JobSubCategoryDto createdJobSubCategoryDto = jobSubCategoryService.createJobSubcategory(jobSubCategoryDto);
        logger.info("JobCategory created with ID: {}", createdJobSubCategoryDto.getId());
        return new ResponseEntity<>(createdJobSubCategoryDto, HttpStatus.CREATED);
    }

    /**
     * Deletes a JobSubCategory by ID.
     *
     * @param id The ID of the JobSubCategory to be deleted.
     * @return HTTP status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSubCategory(@PathVariable Long id) {
        logger.info("Deleting JobSubCategory with ID: {}", id);
        jobSubCategoryService.deleteJobSubcategory(id);
        logger.info("JobSubCategory with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a list of all JobCategory.
     *
     * @return A list of JobSubCategory DTOs with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<JobSubCategoryDto>> getAllJobSubCategories() {
        logger.info("Retrieving list of all JobSubCategory");
        List<JobSubCategoryDto> jobSubCategoryDtos = jobSubCategoryService.getAllJobSubCategories();
        logger.info("Retrieved {} JobSubCategorys", jobSubCategoryDtos.size());
        return new ResponseEntity<>(jobSubCategoryDtos, HttpStatus.OK);
    }

    /**
     * Retrieves a JobSubCategory by ID.
     *
     * @param id The ID of the JobCategory.
     * @return The JobSubCategory DTO with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobSubCategoryDto> getJobSubCategoryById(@PathVariable Long id) {
        logger.info("Retrieving JobSubCategory with ID: {}", id);
        JobSubCategoryDto jobSubCategoryDto = jobSubCategoryService.getJobSubCategoryById(id);
        logger.info("Retrieved JobSubCategory with ID: {}", id);
        return new ResponseEntity<>(jobSubCategoryDto, HttpStatus.OK);
    }

    /**
     * Updates an JobSubCategory.
     *
     * @param id The ID of the jobCategory to be updated.
     * @param jobSubCategoryDto {@link JobSubCategoryDto} The DTO containing updated jobCategory data.
     * @return The updated jobCategory DTO with HTTP status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobSubCategoryDto> updateJobSubCategory(@PathVariable Long id, @RequestBody JobSubCategoryDto jobSubCategoryDto) {
        logger.info("Updating jobSubCategory with ID: {}", id);
        JobSubCategoryDto updatedjobSubCategoryDto = jobSubCategoryService.updateJobSubcategory(id, jobSubCategoryDto);
        logger.info("Updated jobSubCategory with ID: {}", id);
        return new ResponseEntity<>(updatedjobSubCategoryDto, HttpStatus.OK);
    }

}
