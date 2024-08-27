package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.service.EmployerService;
import com.ideas2it.hirezy.service.JobPostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * REST controller for managing Employer entities.
 * Provides endpoints to create, retrieve, update, delete Employer,create,update,delete jobposts.
 */
@RestController
@RequestMapping("api/v1/employers")
public class EmployerController {
    private static final Logger logger = LogManager.getLogger(EmployerController.class);

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobPostService jobPostService;

    /**
     * <p>
     *This method is used to create a employer into the repository
     * </p>
     */
    @PostMapping()
    public ResponseEntity<EmployerDto> addEmployer(@RequestBody EmployerDto employerDto) {
        logger.info("Request received to add a new employer: {}", employerDto.getName());
        EmployerDto savedEmployer = employerService.createEmployer(employerDto);
        logger.info("Employer created with ID: {}", savedEmployer.getId());
        return new ResponseEntity<>((savedEmployer), HttpStatus.CREATED);
    }

    /**
     * <p>
     *This method is used to show all the employer in the repository
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<EmployerDto>>  DisplayAllEmployer() {
        logger.info("Request received to get all employers");
        List<EmployerDto> employers  = employerService.getAllEmployer();
        logger.info("Returning {} employers", employers.size());
        return new ResponseEntity<>(employers, HttpStatus.OK);

    }

    /**
     * <p>
     *This method is used to show a specific employer in the repository
     * @param employerId - unique identifier of the employer
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> displayEmployer(@PathVariable("id") long employerId) {
        logger.info("Request received to get employer with ID: {}", employerId);
        EmployerDto showEmployerDto = employerService.getEmployerById(employerId);
        if (showEmployerDto != null) {
            logger.info("Employer found with ID: {}", employerId);
            return new ResponseEntity<>(showEmployerDto, HttpStatus.OK);
        } else {
            logger.warn("Employer not found with ID: {}", employerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * <p>
     *This method is used to create a employer into the repository
     * @param employerId -unique identifier of employer
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployerDto> updateEmployer(@PathVariable("id") int employerId, @RequestBody EmployerDto employerDto) {
        logger.info("Request received to update employer with ID: {}", employerId);
        EmployerDto updateEmployerDto =  employerService.updateEmployer(employerId, employerDto);
        logger.info("Employer with ID {} has been updated", employerId);
        return new ResponseEntity<>((updateEmployerDto),HttpStatus.OK);
    }

    /**
     * <p>
     *This method is used to delete a employer from the repository
     * @param employerId
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable("id") int employerId) {
        logger.info("Request received to delete employer with ID: {}", employerId);
        employerService.removeEmployer(employerId);
        logger.info("Employer with ID {} has been deleted", employerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * <p>
     * This method is used to create a job post for a specific employer.
     * @param employerId - unique identifier of the employer
     * @param jobPostDto - job post details
     * </p>
     */
    @PostMapping("/{employerId}/job-posts")
    public ResponseEntity<JobPostDto> createJobPost(
            @PathVariable Long employerId,
            @RequestBody JobPostDto jobPostDto) {
        logger.info("Request received to create a job post for employer ID: {}", employerId);
        JobPostDto createdJobPost = jobPostService.createJobPost(employerId, jobPostDto);
        logger.info("Job post created with ID: {}", createdJobPost.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJobPost);
    }

    /**
     * <p>
     * This method is used to update a job post for a specific employer.
     * @param employerId - unique identifier of the employer
     * @param jobId - unique identifier of the job post
     * @param jobPostDto - updated job post details
     * </p>
     */
    @PutMapping("/{employerId}/job-posts/{jobId}")
    public ResponseEntity<JobPostDto> updateJobPost(
            @PathVariable Long employerId,
            @PathVariable Long jobId,
            @RequestBody JobPostDto jobPostDto) {
        logger.info("Request received to update job post with ID: {} for employer ID: {}", jobId, employerId);
        JobPostDto updatedJobPost = employerService.updateJobPost(employerId, jobId, jobPostDto);
        logger.info("Job post with ID {} has been updated", jobId);
        return new ResponseEntity<>(updatedJobPost, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to delete a job post for a specific employer.
     * @param employerId - unique identifier of the employer
     * @param jobId - unique identifier of the job post
     * </p>
     */
    @DeleteMapping("/{employerId}/job-posts/{jobId}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long employerId, @PathVariable Long jobId) {
        logger.info("Request received to delete job post with ID: {} for employer ID: {}", jobId, employerId);
        employerService.deleteJobPost(jobId);
        logger.info("Job post with ID {} has been deleted", jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * <p>
     * This method is used to get all job posts for a specific employer.
     * @param employerId - unique identifier of the employer
     * </p>
     */
    @GetMapping("/{employerId}/job-posts")
    public ResponseEntity<List<JobPostDto>> getAllJobPostsByEmployer(@PathVariable Long employerId) {
        logger.info("Request received to get all job posts for employer ID: {}", employerId);
        List<JobPostDto> jobPosts = employerService.getAllJobPostsByEmployer(employerId);
        logger.info("Returning {} job posts for employer ID: {}", jobPosts.size(), employerId);
        return new ResponseEntity<>(jobPosts, HttpStatus.OK);
    }
}
