package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobApplication;
import com.ideas2it.hirezy.service.EmployeeService;
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
@RequestMapping("api/v1/employers/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;


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

    @PostMapping("/employee/{employeeId}/job-post/{jobPostId}")
    public ResponseEntity<JobApplication> applyForJobPostByEmployee(@PathVariable Long employeeId, @PathVariable Long jobPostId) {
       JobApplication jobApplication = jobApplicationService.applyJobByEmployee(employeeId, jobPostId);
        return  new ResponseEntity<>(jobApplication, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplicationDto> updateApplicationStatus(@PathVariable Long id,@RequestParam String status) {
        JobApplicationDto updatedJobApplication = jobApplicationService.updateApplicationStatus(id, status);
        return new ResponseEntity<>(updatedJobApplication,HttpStatus.OK);
    }

    @GetMapping("/jobpost/{jobPostId}")
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationByJobPostId(@PathVariable Long jobPostId) {
        List<JobApplicationDto> jobApplications = jobApplicationService.getJobApplicationByjobPostId(jobPostId);
        if(jobApplications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jobApplications,HttpStatus.OK);
    }
}
