package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.service.EmployerService;
import com.ideas2it.hirezy.service.JobPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerControllerTest {
    @Mock
    private EmployerService employerService;

    @Mock
    private JobPostService jobPostService;

    @InjectMocks
    private EmployerController employerController;

    private EmployerDto employerDto;
    private JobPostDto jobPostDto;
    private List<EmployerDto> employerList;

    @BeforeEach
    void setUp() {
        employerDto = new EmployerDto();
        employerDto.setId(1L);
        employerDto.setName("Kishore");

        jobPostDto = new JobPostDto();
        jobPostDto.setId(101L);
        jobPostDto.setTitle("Software Developer");
        jobPostDto.setEmployerId(1L);
        employerList = Arrays.asList(employerDto);
    }

    @Test
    void testAddEmployer() {
        when(employerService.createEmployer(any(EmployerDto.class))).thenReturn(employerDto);
        ResponseEntity<EmployerDto> response = employerController.addEmployer(employerDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employerDto, response.getBody());
        verify(employerService, times(1)).createEmployer(any(EmployerDto.class));
    }

    @Test
    void testDisplayAllEmployers() {
        when(employerService.getAllEmployers()).thenReturn(employerList);
        ResponseEntity<List<EmployerDto>> response = employerController.DisplayAllEmployer();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employerList, response.getBody());
        verify(employerService, times(1)).getAllEmployers();
    }

    @Test
    void testDisplayEmployer_Found() {
        when(employerService.getEmployerById(1L)).thenReturn(employerDto);
        ResponseEntity<EmployerDto> response = employerController.displayEmployer(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employerDto, response.getBody());
        verify(employerService, times(1)).getEmployerById(1L);
    }

    @Test
    void testDisplayEmployer_NotFound() {
        when(employerService.getEmployerById(1L)).thenReturn(null);
        ResponseEntity<EmployerDto> response = employerController.displayEmployer(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(employerService, times(1)).getEmployerById(1L);
    }

    @Test
    void testUpdateEmployer() {
        when(employerService.updateEmployer(any(EmployerDto.class))).thenReturn(employerDto);
        ResponseEntity<EmployerDto> response = employerController.updateEmployer(employerDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employerDto, response.getBody());
        verify(employerService, times(1)).updateEmployer(any(EmployerDto.class));
    }

    @Test
    void testDeleteEmployer() {
        ResponseEntity<Void> response = employerController.deleteEmployer(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employerService, times(1)).removeEmployer(1L);
    }

    @Test
    void testCreateJobPost() {
        when(jobPostService.createJobPost(anyLong(), any(JobPostDto.class))).thenReturn(jobPostDto);
        ResponseEntity<JobPostDto> response = employerController.createJobPost(1L, jobPostDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jobPostDto, response.getBody());
        verify(jobPostService, times(1)).createJobPost(anyLong(), any(JobPostDto.class));
    }

    @Test
    void testUpdateJobPost() {
        when(employerService.updateJobPost(anyLong(), anyLong(), any(JobPostDto.class))).thenReturn(jobPostDto);
        ResponseEntity<JobPostDto> response = employerController.updateJobPost(1L, 101L, jobPostDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobPostDto, response.getBody());
        verify(employerService, times(1)).updateJobPost(anyLong(), anyLong(), any(JobPostDto.class));
    }

    @Test
    void testDeleteJobPost() {
        ResponseEntity<Void> response = employerController.deleteJobPost(1L, 101L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employerService, times(1)).deleteJobPost(101L);
    }

    @Test
    void testGetAllJobPostsByEmployer() {
        List<JobPostDto> jobPosts = Arrays.asList(jobPostDto);
        when(employerService.getAllJobPostsByEmployer(1L)).thenReturn(jobPosts);
        ResponseEntity<List<JobPostDto>> response = employerController.getAllJobPostsByEmployer(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobPosts, response.getBody());
        verify(employerService, times(1)).getAllJobPostsByEmployer(1L);
    }

}