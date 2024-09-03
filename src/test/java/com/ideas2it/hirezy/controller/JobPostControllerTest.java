package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobPostDto;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobPostControllerTest {
    @Mock
    private JobPostService jobPostService;

    @InjectMocks
    private JobPostController jobPostController;

    private JobPostDto jobPostDto;

    @BeforeEach
    void setUp() {
        jobPostDto = JobPostDto.builder()
                .id(1L)
                .title("Software Engineer")
                .state("TamilNadu")
                .city("Chennai")
                .experience(3)
                .build();
    }

    @Test
    void testGetAllJobs() {
        List<JobPostDto> jobPosts = Arrays.asList(jobPostDto);
        when(jobPostService.getAllJobs()).thenReturn(jobPosts);
        ResponseEntity<List<JobPostDto>> response = jobPostController.getAllJobs();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobPosts, response.getBody());
        verify(jobPostService, times(1)).getAllJobs();
    }

    @Test
    void testGetJobByIdFound() {
        when(jobPostService.getJobById(1L)).thenReturn(jobPostDto);
        ResponseEntity<JobPostDto> response = jobPostController.getJobById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobPostDto, response.getBody());
        verify(jobPostService, times(1)).getJobById(1L);
    }

    @Test
    void testGetJobByIdNotFound() {
        when(jobPostService.getJobById(1L)).thenReturn(null);
        ResponseEntity<JobPostDto> response = jobPostController.getJobById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(jobPostService, times(1)).getJobById(1L);
    }

    @Test
    void testSearchJobs() {
        List<JobPostDto> expectedJobs = Arrays.asList(jobPostDto);
        when(jobPostService.searchJobsByFilters("Tamil Nadu", "Chennai", "IT", "Software", null, "Private", "Software", 3, Arrays.asList("Java", "Spring")))
                .thenReturn(expectedJobs);
        ResponseEntity<List<JobPostDto>> response = jobPostController.searchJobs("Tamil Nadu", "Chennai", "IT", "Software", null, "Private", "Software", 3, Arrays.asList("Java", "Spring"));
        assertEquals(expectedJobs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        when(jobPostService.searchJobsByFilters(null, "Chennai", null, null, null, null, null, null, null))
                .thenReturn(expectedJobs);
        response = jobPostController.searchJobs(null, "Chennai", null, null, null, null, null, null, null);
        assertEquals(expectedJobs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        when(jobPostService.searchJobsByFilters(null, null, null, null, null, null, null, 3, null))
                .thenReturn(expectedJobs);
        response = jobPostController.searchJobs(null, null, null, null, null, null, null, 3, null);
        assertEquals(expectedJobs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        when(jobPostService.searchJobsByFilters(null, null, null, null, null, null, null, null, null))
                .thenReturn(expectedJobs);
        response = jobPostController.searchJobs(null, null, null, null, null, null, null, null, null);
        assertEquals(expectedJobs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}