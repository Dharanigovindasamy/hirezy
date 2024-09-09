package com.ideas2it.hirezy.controller;

import java.util.List;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import com.ideas2it.hirezy.service.FeedbackService;
import com.ideas2it.hirezy.service.JobPostService;
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.service.EmployeeService;

/**
 * <p>
 *  This class holds employee profile operations like adding, retrieving, updating and deleting
 *  The user data which can be sent as json format and stored as entity in the server table
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private JobPostService jobPostService;

    private static final Logger logger = LogManager.getLogger(EmployerController.class);

    /**
     * <p>
     * Adding employee profile details into the table as HTTP json data format
     * </p>
     * @param employeeDto - {@link EmployeeDto} employeeDto receive from user as json format
     * @return employeeDto - employeeDto after adding into the table
     *
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        logger.info(ResponseEntity.ok("Files uploaded successfully"));
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    /**
     * <p>
     *     Retrieve all employee profile details from the table
     * </p>
     * @return <List<EmployeeDto>> - list of employee profile
     *
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayEmployees() {
        return new ResponseEntity<>(employeeService.retrieveEmployees(), HttpStatus.OK);
    }

    /**
     * <p>
     *     Display employee details By employee Id
     * </p>
     * @param employeeId - Id of the employee
     * @return employeeDto - employeeDto of the respective employee Id
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeById(@PathVariable("id") Long employeeId) {
       EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Update employee by checking with employee id
     * </p>
     * @param employeeDto - {@link EmployeeDto}
     * @return EmployeeDto - employeeDto to the user
     *
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeDto = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Delete employee by giving employee id
     * </p>
     * @param employeeId - employeeId of the employee
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create a new feedback/query.
     * @param feedbackDto {link @FeedbackDto} receive from user as json format
     * @return The created feedback DTO with HTTP status 201 Created.
     */
    @PostMapping("/feedback")
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setFeedBackType(FeedbackType.EMPLOYEE);
        FeedbackDto createdFeedback = feedbackService.createFeedback(feedbackDto);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    /**
     * Retrieves a specific feedback by its id and user id.
     * @param feedbackId Id of feedback
     * @param userId Id of employee
     * @return The feedbackDTO with HTTP status 200 OK.
     */
    @GetMapping("/{userId}/feedback/{feedbackId}")
    public ResponseEntity<FeedbackDto> getFeedback(
            @PathVariable Long feedbackId,
            @PathVariable Long userId) {
        FeedbackDto feedback = feedbackService.getFeedbackByIdUserIdAndType(feedbackId, userId, FeedbackType.EMPLOYEE);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    /**
     * Retrieves jobpost with an employee's profile based on key-skills, city and experience.
     * @param employeeId employee whose profile will be matched with jobposts.
     * @return A list of JobPostDto that match with employee's profile with HTTP status 200 OK.
     */
    @GetMapping("/{employeeId}/auto-match-jobposts")
    public ResponseEntity<List<JobPostDto>> autoMatchJobs(@PathVariable Long employeeId) {
        List<JobPostDto> matchedJobs = jobPostService.autoMatchJobPostsWithEmployee(employeeId);
        return new ResponseEntity<>(matchedJobs,HttpStatus.OK);
    }
}
