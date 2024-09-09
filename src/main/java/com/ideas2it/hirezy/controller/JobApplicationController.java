package com.ideas2it.hirezy.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.service.JobApplicationService;

/**
 * <p>
 *  This class holds job application profile operations like adding, retrieving, updating and deleting
 *  The user data which can be sent as json format and stored as entity in the server table
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    private static final Logger logger = LogManager.getLogger(JobApplicationController.class);
    /**
     * <p>
     *     Retrieve all job application profile details from the table
     *
     * @return <List<JobApplicationDto>> - list of Job Application profile
     * </p>
     */
    @Operation(summary = "Retrieve all job application")
    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
        logger.info("Displaying all Job Applications successfully");
        return new ResponseEntity<>(jobApplicationService.getAllJobApplications(), HttpStatus.OK);
    }

    /**
     * <p>Display employee details By employee Id
     *
     * @param id - Id of the Job Application
     * @return JobApplicationDto - JobApplicationDto of the respective Id
     * </p>
     *
     */
    @Operation(summary = "Get JobApllication By ID")
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable Long id) {
        JobApplicationDto jobApplicationDto = jobApplicationService.getJobApplicationById(id);
        logger.info("Displaying Job Applications successfully by {}", id);
        return new ResponseEntity<>(jobApplicationDto, HttpStatus.OK);
    }


    /**
     * <p>Withdraw job Application by giving job Application id
     *
     * @param id - job ApplicationId of the job Application
     * </p>
     */
    @Operation(summary = "Withdraw Job Application by giving job application Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEmployeeJobApplication(@PathVariable Long id) {
        logger.info("Job Application Withdraw successfully by {}", id);
        return new ResponseEntity<>(jobApplicationService.removeJobApplicationForEmployee(id),HttpStatus.NO_CONTENT);
    }

    /**
     * <p>
     *     Update job application status
     * </p>
     *
     * @param id - id of the job application
     * @param status - status to be updated in jon application
     * @return JobApplicationDto - {@link JobApplicationDto} job application details after updating the status
     */
    @Operation(summary = "Update job application")
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('EMPLOYER') and !hasRole('ADMIN') and !hasRole('EMPLOYEE')")
    public ResponseEntity<JobApplicationDto> updateApplicationStatus(@PathVariable Long id,@RequestParam String status) {
        logger.info("Job Applications updated successfully {}", id);
        JobApplicationDto updatedJobApplication = jobApplicationService.updateApplicationStatus(id, status);
        return new ResponseEntity<>(updatedJobApplication,HttpStatus.OK);
    }

    /**
     * <p>
     *     Retrieving job applications under the job post by giving job Post id
     * </p>
     * @param jobPostId - id of the job post
     * @return List<JobApplicationDto> - list of job applications under the job post
     */
    @Operation(summary = "Retrieving job applications under the job post by giving job Post id")
    @GetMapping("/job-post/{jobPostId}")
    @PreAuthorize("hasRole('EMPLOYER') and !hasRole('ADMIN') and !hasRole('EMPLOYEE')")
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationByJobPostId(@PathVariable Long jobPostId) {
        List<JobApplicationDto> jobApplications = jobApplicationService.getJobApplicationByJobPostId(jobPostId);
        if(jobApplications.isEmpty()) {
            logger.warn("No job application under job post {}", jobPostId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Display Job Applications successfully by job post ID {} ", jobPostId);
        return new ResponseEntity<>(jobApplications,HttpStatus.OK);
    }

    /**
     * This method is to assign job to the employee.
     * @param employeeId
     *     It is the id of the employee to be assigned.
     * @param jobPostId
     *     It is the job id which is to be assigned.
     * @return String
     *     It is the success message to be returned to the employee.
     */
    @Operation(summary = "Apply job to the employee")
    @PutMapping("/job-post/{jobPostId}/employee/{employeeId}")
    @PreAuthorize("hasRole('EMPLOYEE') and !hasRole('ADMIN') and !hasRole('EMPLOYER')")
    public ResponseEntity<String> applyForJob(@PathVariable long jobPostId,@PathVariable long employeeId) {
        logger.info("Job Application applied successfully by {}",employeeId );
        return  new ResponseEntity<>(jobApplicationService.applyJob(employeeId,jobPostId),HttpStatus.OK);
    }

    /**
     * This method is to retrieve the jobs applied by the employee
     * @param employeeId
     *     It is the id of the employee to get their jobs applied.
     * @return List<JobApplicationDto>
     *     It contains the list of jobs applied by the employee.
     */
    @Operation(summary = "Retrieve the jobs applied by the employee")
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasRole('EMPLOYEE') and !hasRole('ADMIN') and !hasRole('EMPLOYER')")
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationByEmployee(@PathVariable Long employeeId) {
        List<JobApplicationDto> jobApplicationDto = jobApplicationService.retrieveEmployeeAppliedJobs(employeeId);
        logger.info("Displaying all Job Applications successfully {}", employeeId );
        return new ResponseEntity<>(jobApplicationDto,HttpStatus.OK);
    }
}
