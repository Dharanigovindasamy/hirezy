package com.ideas2it.hirezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/v1/employee/job-applications")
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
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable Long id) {
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
    public ResponseEntity<String> removeJobApplication(@PathVariable Long id) {
        return new ResponseEntity<>(jobApplicationService.removeJobApplicationForEmployee(id),HttpStatus.OK);
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

    /**
     * This method is to assign job to the employee.
     * @param employeeId
     *     It is the id of the employee to be assigned.
     * @param jobPostId
     *     It is the job id which is to be assigned.
     * @return String
     *     It is the success message to be returned to the employee.
     */
    @PutMapping("/jobPost/{jobPostId}/employee/{employeeId}")
    public ResponseEntity<String> applyForJob(@PathVariable long jobPostId,@PathVariable long employeeId) {
        return  new ResponseEntity<>(jobApplicationService.applyJob(employeeId,jobPostId),HttpStatus.OK);
    }

    /**
     * This method is to retrieve the jobs applied by the employee
     * @param employeeId
     *     It is the id of the employee to get their jobs applied.
     * @return List<JobApplicationDto>
     *     It contains the list of jobs applied by the employee.
     */

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationByEmployee(@PathVariable Long employeeId) {
        List<JobApplicationDto> jobApplicationDto = jobApplicationService.retrieveEmployeeAppliedJobs(employeeId);
        return new ResponseEntity<>(jobApplicationDto,HttpStatus.OK);
    }
}
