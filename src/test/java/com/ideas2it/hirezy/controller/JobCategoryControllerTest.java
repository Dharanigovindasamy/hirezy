package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.service.JobCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobCategoryControllerTest {

    @Mock
    private JobCategoryService jobCategoryService;

    @InjectMocks
    private JobCategoryController jobCategoryController;

    private JobCategory jobCategory;
    private JobCategoryDto jobCategoryDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        jobCategory = new JobCategory();
        jobCategory.setId(1L);
        jobCategory.setName("IT");

        jobCategoryDto = new JobCategoryDto();
        jobCategoryDto.setId(1L);
        jobCategoryDto.setName("IT");
    }

    @Test
    void testAddJobCategory() {
        when(jobCategoryService.createJobCategory(jobCategoryDto)).thenReturn(jobCategoryDto);
        ResponseEntity<JobCategoryDto> response = jobCategoryController.addJobCategory(jobCategoryDto);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void testDisplayJobCategory() {
        when(jobCategoryService.getAllJobCategories()).thenReturn(List.of(jobCategoryDto));
        ResponseEntity<List<JobCategoryDto>> response = jobCategoryController.getAllJobCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void testDisplayJobCategoryById() {
        when(jobCategoryService.getJobCategoryById(jobCategoryDto.getId())).thenReturn(jobCategoryDto);
        ResponseEntity<JobCategoryDto> response = jobCategoryController.getJobCategoryById(jobCategoryDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobCategoryDto.getName(), response.getBody().getName());
    }

    @Test
    void testUpdateJobCategory() {
        when(jobCategoryService.updateJobCategory(jobCategoryDto.getId(),jobCategoryDto)).thenReturn(jobCategoryDto);
        ResponseEntity<JobCategoryDto> response = jobCategoryController.updateJobCategory(jobCategoryDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobCategoryDto.getName(), response.getBody().getName());
    }

    @Test
    void testDeleteJobCategory() {
        doNothing().when(jobCategoryService).deleteJobCategory(jobCategoryDto.getId());
        ResponseEntity<Void> response = jobCategoryController.deleteJobCategory(jobCategoryDto.getId());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}
