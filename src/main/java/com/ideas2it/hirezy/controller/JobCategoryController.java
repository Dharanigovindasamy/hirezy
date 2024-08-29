package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.service.JobCategoryService;
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
 * REST controller for managing JobCategory entities.
 * Provides endpoints to create, retrieve, update, delete JobCategory.
 */
@RestController
@RequestMapping("/api/v1/category")
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    private static final Logger logger = LogManager.getLogger(JobCategoryController.class);

    /**
     * Creates a new JobCategory.
     *
     * @param jobCategoryDto {@link JobCategoryDto} The DTO containing JobCategory data.
     * @return The created JobCategory DTO with HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<JobCategoryDto> addJobCategory(@RequestBody JobCategoryDto jobCategoryDto) {
        logger.info("Request to create JobCategory with details: {}", jobCategoryDto);
        JobCategoryDto createdJobCategoryDto = jobCategoryService.createJobCategory(jobCategoryDto);
        logger.info("JobCategory created with ID: {}", createdJobCategoryDto.getId());
        return new ResponseEntity<>(createdJobCategoryDto, HttpStatus.CREATED);
    }

    /**
     * Deletes a JobCategory by ID.
     *
     * @param id The ID of the JobCategory to be deleted.
     * @return HTTP status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobCategory(@PathVariable Long id) {
        logger.info("Deleting JobCategory with ID: {}", id);
        jobCategoryService.deleteJobCategory(id);
        logger.info("JobCategory with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a list of JobCategory.
     *
     * @return A list of JobCategory DTOs with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<JobCategoryDto>> getAllJobCategories() {
        logger.info("Retrieving list of all JobCategory");
        List<JobCategoryDto> jobCategoryDtos = jobCategoryService.getAllJobCategories();
        logger.info("Retrieved {} JobCategory", jobCategoryDtos.size());
        return new ResponseEntity<>(jobCategoryDtos, HttpStatus.OK);
    }

    /**
     * Retrieves a JobCategory by ID.
     *
     * @param id The ID of the JobCategory.
     * @return The JobCategory DTO with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryDto> getJobCategoryById(@PathVariable Long id) {
        logger.info("Retrieving JobCategory with ID: {}", id);
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(id);
        logger.info("Retrieved JobCategory with ID: {}", id);
        return new ResponseEntity<>(jobCategoryDto, HttpStatus.OK);
    }

    /**
     * Updates an JobCategory.
     *
     * @param id The ID of the jobCategory to be updated.
     * @param jobCategoryDto {@link JobCategoryDto} The DTO containing updated jobCategory data.
     * @return The updated jobCategory DTO with HTTP status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryDto> updateJobCategory(@PathVariable Long id, @RequestBody JobCategoryDto jobCategoryDto) {
        logger.info("Updating jobCategory with ID: {}", id);
        JobCategoryDto updatedjobCategoryDto = jobCategoryService.updateJobCategory(id, jobCategoryDto);
        logger.info("Updated jobCategory with ID: {}", id);
        return new ResponseEntity<>(updatedjobCategoryDto, HttpStatus.OK);
    }
}
