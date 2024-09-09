package com.ideas2it.hirezy.controller;


import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.service.EmployeeService;
import com.ideas2it.hirezy.service.EmployerService;
import com.ideas2it.hirezy.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private FeedbackService feedbackService;

    /**
     *  Display the user counts
     * @return count of active & deleted employee & employer.
     */
    @Operation(summary = "Display count of active & deleted employee & employer")
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String,Map<String,Long>>>getCounts() {
        Long activeEmployerCount = employerService.countActiveEmployers();
        Long deletedEmployerCount = employerService.countDeletedEmployers();
        Long activeEmployeeCount = employeeService.countActiveEmployees();
        Long deletedEmployeeCount = employeeService.countDeletedEmployees();

        Map<String,Long> employerCounts = new HashMap<>();
        employerCounts.put("active",activeEmployerCount);
        employerCounts.put("deleted",deletedEmployerCount);
        Map<String,Long> employeeCounts = new HashMap<>();
        employeeCounts.put("active",activeEmployeeCount);
        employeeCounts.put("deleted",deletedEmployeeCount);
        Map<String,Map<String,Long>> counts =new HashMap<>();
        counts.put("employer",employerCounts);
        counts.put("employee",employeeCounts);
        return ResponseEntity.ok(counts);
    }

    /**
     * Adds a reply to an existing feedback.
     * @param feedbackId Id of feedback
     * @param replyContent the content of body.
     * @return The updated FeedbackDto with the reply and HTTp status code ok.
     */
    @Operation(summary = "Reply to an feedback/query")
    @PostMapping("/{feedbackId}/reply")
    public ResponseEntity<FeedbackDto> replyToFeedback(
            @PathVariable Long feedbackId,
            @RequestBody String replyContent) {
        FeedbackDto updatedFeedback = feedbackService.replyToFeedback(feedbackId, replyContent);
        return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
    }

    /**
     * Retrieve all feedback  of employee & employer from the table
     * @return List of feedback.
     */
    @Operation(summary = "retrieve all feedback of employee & employer")
    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackDto>>getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks,HttpStatus.OK);
    }
}
