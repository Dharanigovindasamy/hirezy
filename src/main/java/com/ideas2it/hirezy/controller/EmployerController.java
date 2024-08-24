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

@RestController
@RequestMapping("api/v1/employer")
public class EmployerController {
    private static final Logger logger = LogManager.getLogger();

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
    public ResponseEntity<EmployerDto> addEmployer(@RequestBody EmployerDto employerDto){
        EmployerDto savedEmployer = employerService.createEmployer(employerDto);
        return new ResponseEntity<>((savedEmployer), HttpStatus.CREATED);
    }
    
    /**
     * <p>
     *This method is used to show all the employer in the repository
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<EmployerDto>>  DisplayAllCompanies() {
        List<EmployerDto> companies  = employerService.getAllEmployer();
        return new ResponseEntity<>(companies, HttpStatus.OK);

    }
    
    /**
     * <p>
     *This method is used to show a specific employer in the repository
     * @param employerId - unique identifier of the employer
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> displayEmployer(@PathVariable("id") int employerId) {
        EmployerDto showemployerDto = employerService.getEmployerById(employerId);
        return new ResponseEntity<>((showemployerDto),HttpStatus.OK);
    }
    
    /**
     * <p>
     *This method is used to create a employer into the repository
     * @param employerId -unique identifier of employer
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployerDto> updateEmployer(@PathVariable("id") int employerId, @RequestBody EmployerDto employerDto) {
        EmployerDto updateEmployerDto =  employerService.updateEmployer(employerId, employerDto);
        logger.info("the details of the company have been updated of id..{}",employerId);
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
        employerService.removeEmployer(employerId);
        logger.info("employer of this id  has been deleted..{}",employerId);
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
    public ResponseEntity<JobPostDto> createJobPost(@PathVariable Long employerId, @RequestBody JobPostDto jobPostDto) {
        EmployerDto employerDto = new EmployerDto();
        employerDto.setId(employerId);
        jobPostDto.setEmployer(employerDto);
        JobPostDto createdJobPost = jobPostService.createJobPost(jobPostDto);
        return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
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
    public ResponseEntity<JobPostDto> updateJobPost(@PathVariable Long employerId, @PathVariable Long jobId, @RequestBody JobPostDto jobPostDto) {
        JobPostDto updatedJobPost = employerService.updateJobPost(jobId, jobPostDto);
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
        employerService.deleteJobPost(jobId);
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
        List<JobPostDto> jobPosts = employerService.getAllJobPostsByEmployer(employerId);
        return new ResponseEntity<>(jobPosts, HttpStatus.OK);
    }
}
