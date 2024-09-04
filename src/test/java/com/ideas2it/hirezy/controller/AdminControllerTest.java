package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.service.EmployeeServiceImpl;
import com.ideas2it.hirezy.service.EmployerServiceImpl;
import com.ideas2it.hirezy.service.FeedbackServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private EmployerServiceImpl employerService;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private FeedbackServiceImpl feedbackService;

    @InjectMocks
    private AdminController adminController;


    @Test
    void testGetCounts_Success() {
        when(employerService.countActiveEmployers()).thenReturn(5L);
        when(employerService.countDeletedEmployers()).thenReturn(2L);
        when(employeeService.countActiveEmployees()).thenReturn(10L);
        when(employeeService.countDeletedEmployees()).thenReturn(1L);
        ResponseEntity<Map<String, Map<String, Long>>> response = adminController.getCounts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Map<String, Long>> counts = response.getBody();
        assert counts != null;
        assertEquals(5L, counts.get("employer").get("active"));
        assertEquals(2L, counts.get("employer").get("deleted"));
        assertEquals(10L, counts.get("employee").get("active"));
        assertEquals(1L, counts.get("employee").get("deleted"));
    }

    @Test
    void testReplyToFeedback_Success() {
        Long feedbackId = 1L;
        String replyContent = "Thank you for your feedback";
        FeedbackDto feedbackDto = new FeedbackDto(); // Mock FeedbackDto creation
        feedbackDto.setId(feedbackId);
        feedbackDto.setAdminReply(replyContent);
        when(feedbackService.replyToFeedback(feedbackId, replyContent)).thenReturn(feedbackDto);
        ResponseEntity<FeedbackDto> response = adminController.replyToFeedback(feedbackId, replyContent);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feedbackDto, response.getBody());
    }

    @Test
    void testGetAllFeedbacks_Success() {
        FeedbackDto feedback1 = new FeedbackDto();
        feedback1.setId(1L);
        FeedbackDto feedback2 = new FeedbackDto();
        feedback2.setId(2L);
        List<FeedbackDto> feedbackList = Arrays.asList(feedback1, feedback2);
        when(feedbackService.getAllFeedbacks()).thenReturn(feedbackList);
        ResponseEntity<List<FeedbackDto>> response = adminController.getAllFeedbacks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feedbackList, response.getBody());
    }

}