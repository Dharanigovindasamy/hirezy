package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("api/v1/jobApplications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;


    /**
     * <p>
     * Adding job application profile details into the table as HTTP json data format
     *
     * @param jobApplicationDto - {@link JobApplicationDto} Job Application Dto receive from user as json format
     * @return JobApplicationDto - Job Application Dto after adding into the table
     * </p>
     */
    @PostMapping
    public ResponseEntity<JobApplicationDto> addJobApplication(@Valid @RequestBody JobApplicationDto jobApplicationDto) {
        return new ResponseEntity<>(jobApplicationService.addJobApplication(jobApplicationDto), HttpStatus.CREATED);
    }

    /**
     * <p>
     *     Retrieve all job application profile details from the table
     *
     * @return <List<JobApplicationDto>> - list of Job Application profile
     * </p>
     */
    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
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
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable("id") Long id) {
        JobApplicationDto jobApplicationDto = jobApplicationService.getJobApplicationById(id);
        return new ResponseEntity<>(jobApplicationDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Update Job Application Dto by checking with JobApplication id
     *
     * @param jobApplicationDto - {@link JobApplicationDto}
     * @return JobApplicationDto - job application dto of the respective id
     * </p>
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationDto> updateJobApplication(@RequestBody JobApplicationDto jobApplicationDto) {
        jobApplicationDto = jobApplicationService.updateJobApplication(jobApplicationDto);
        return new ResponseEntity<>(jobApplicationDto, HttpStatus.OK);
    }

    /**
     * <p>Delete job Application by giving job Application id
     *
     * @param id - job ApplicationId of the job Application
     * </p>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeJobApplication(@PathVariable("id") Long id) {
        jobApplicationService.removeJobApplication(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
