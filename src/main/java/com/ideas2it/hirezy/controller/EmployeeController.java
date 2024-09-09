package com.ideas2it.hirezy.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import com.ideas2it.hirezy.service.EmployeeService;
import com.ideas2it.hirezy.service.FeedbackService;
import com.ideas2it.hirezy.service.JobPostService;
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

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    /**
     * <p>
     * Adding employee profile details into the table as HTTP json data format
     * </p>
     * @param employeeDto - {@link EmployeeDto} employeeDto receive from user as json format
     * @return employeeDto - employeeDto after adding into the table
     *
     */
    @Operation(summary = "Create a employee Profile")
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        logger.info(ResponseEntity.ok("Employees added successfully"));
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    /**
     * <p>
     *     Retrieve all employee profile details from the table
     * </p>
     * @return <List<EmployeeDto>> - list of employee profile
     *
     */
    @Operation(summary = "Retrieve all employee")
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
    @Operation(summary = "Display employee details by employee Id")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeById(@PathVariable("id") Long employeeId) {
       EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Update employee by checking with employee
     * </p>
     * @param employeeDto - {@link EmployeeDto}
     * @return EmployeeDto - employeeDto to the user
     *
     */
    @Operation(summary = "Update employee ")
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        employeeDto = employeeService.updateEmployee(employeeDto);
        logger.info("Employee updated successfully");
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Delete employee by giving employee id
     * </p>
     * @param employeeId - employeeId of the employee
     *
     */
    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        logger.info("Employee deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create a new feedback/query.
     * @param feedbackDto {@link FeedbackDto} receive from user as json format
     * @return The created feedback DTO with HTTP status 201 Created.
     */
    @Operation(summary = "Create a new feedback/query")
    @PostMapping("/feedback")
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setFeedBackType(FeedbackType.EMPLOYEE);
        FeedbackDto createdFeedback = feedbackService.createFeedback(feedbackDto);
        logger.info("Employee created feedback successfully");
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    /**
     * Retrieves a specific feedback by its id and user id.
     * @param feedbackId Id of feedback
     * @param userId Id of employee
     * @return The feedbackDTO with HTTP status 200 OK.
     */
    @Operation(summary = "Retrieves a specific feedback by its id employee Id")
    @GetMapping("/{userId}/feedback/{feedbackId}")
    public ResponseEntity<FeedbackDto> getFeedback(
            @PathVariable Long feedbackId,
            @PathVariable Long userId) {
        FeedbackDto feedback = feedbackService.getFeedbackByIdUserIdAndType(feedbackId, userId, FeedbackType.EMPLOYEE);
        logger.info("Employee filled feedback retrieve successfully");
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    /**
     * Retrieves job post with an employee's profile based on key-skills, city and experience.
     * @param employeeId employee whose profile will be matched with job posts.
     * @return A list of JobPostDto that match with employee's profile with HTTP status 200 OK.
     */
    @Operation(summary = "Retrieves jobpost with an employee's profile based on key-skills, city and experience.")
    @GetMapping("/{employeeId}/related-jobposts")
    public ResponseEntity<List<JobPostDto>> autoMatchJobs(@PathVariable Long employeeId) {
        List<JobPostDto> matchedJobs = jobPostService.autoMatchJobPostsWithEmployee(employeeId);
        logger.info("Retrieves job post with an employee's profile successfully");
        return new ResponseEntity<>(matchedJobs,HttpStatus.OK);
    }
}
