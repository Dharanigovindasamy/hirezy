package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.service.JobSubCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobSubCategoryControllerTest {

    @Mock
    private JobSubCategoryService jobSubCategoryService;

    @InjectMocks
    private JobSubCategoryController jobSubCategoryController;

    JobCategory jobCategory;
    JobCategoryDto jobCategoryDto;
    JobSubCategory jobSubCategory;
    JobSubCategoryDto jobSubCategoryDto;;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobCategory = (JobCategory.builder()
                .id(1L)
                .name("software engineering").build());
        jobCategoryDto = (JobCategoryDto.builder()
                .id(1L)
                .name("software engineering").build());
        jobSubCategoryDto = JobSubCategoryDto.builder()
                .id(1L)
                .name("front end")
                .jobCategoryId(jobCategoryDto.getId()).build();
        jobSubCategory = JobSubCategory.builder()
                .id(1L)
                .name("front end")
                .jobCategory(JobCategory.builder()
                        .id(1L)
                        .name("software engineering")
                        .build())
                .build();
    }

    @Test
    void testAddJobSubCategory() {
        when(jobSubCategoryService.createJobSubcategory(jobSubCategoryDto)).thenReturn(jobSubCategoryDto);
        ResponseEntity<JobSubCategoryDto> response = jobSubCategoryController.addJobSubCategory(jobSubCategoryDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response);
    }

    @Test
    void testGetAllJobSubCategories() {
        List<JobSubCategoryDto> jobSubCategoryList = Collections.singletonList(jobSubCategoryDto);
        when(jobSubCategoryService.getAllJobSubCategories()).thenReturn(jobSubCategoryList);
        ResponseEntity<List<JobSubCategoryDto>> response = jobSubCategoryController.getAllJobSubCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(jobSubCategoryDto.getName(), response.getBody().get(0).getName());
    }

    @Test
    void testGetJobSubCategoryById() {
        when(jobSubCategoryService.getJobSubCategoryById(jobSubCategoryDto.getId())).thenReturn(jobSubCategoryDto);
        ResponseEntity<JobSubCategoryDto> response = jobSubCategoryController.getJobSubCategoryById(jobSubCategoryDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateJobSubCategory() {
        when(jobSubCategoryService.updateJobSubcategory(jobSubCategoryDto.getId(), jobSubCategoryDto)).thenReturn(jobSubCategoryDto);
        ResponseEntity<JobSubCategoryDto> response = jobSubCategoryController.updateJobSubCategory(jobSubCategoryDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobSubCategoryDto.getName(), response.getBody().getName());
    }

    @Test
    void testDeleteJobSubCategory() {
        doNothing().when(jobSubCategoryService).deleteJobSubcategory(jobSubCategoryDto.getId());
        ResponseEntity<Void> response = jobSubCategoryController.deleteJobSubCategory(jobSubCategoryDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
