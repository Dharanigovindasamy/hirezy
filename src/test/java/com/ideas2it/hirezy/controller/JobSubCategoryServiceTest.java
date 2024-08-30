package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.dto.LocationDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.JobSubCategoryRepository;
import com.ideas2it.hirezy.service.JobCategoryServiceImpl;
import com.ideas2it.hirezy.service.JobSubCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class JobSubCategoryServiceTest {

    @Mock
    private JobSubCategoryRepository jobSubCategoryRepository;

    @InjectMocks
    private JobSubCategoryServiceImpl jobSubCategoryService;
    private JobCategoryServiceImpl jobCategoryService;

    JobSubCategory jobSubCategory;
    JobSubCategoryDto jobSubCategoryDto;;

    public JobSubCategoryServiceTest(JobCategoryServiceImpl jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobSubCategoryDto = JobSubCategoryDto.builder()
                .id(1L)
                .name("front end")
                .jobCategoryId(1L).build();
        jobSubCategory = JobSubCategory.builder()
                .id(1L)
                .name("front end")
                .jobCategory(JobCategory.builder()
                        .id(1L)
                        .name("software engineering").build())
                .build();
    }

   /** @Test
    void testCreateJobSubcategorySuccess() {
        when(jobCategoryService.getJobCategoryById(1L)).thenReturn(jobSubCategory.getJobCategory());
        when(jobSubCategoryRepository.save(any(JobSubCategory.class))).thenReturn(jobSubCategory);
        JobSubCategoryDto result = jobSubCategoryService.createJobSubcategory(jobSubCategoryDto);
        assertNotNull(result);
        assertEquals(jobSubCategoryDto.getJobCategoryId(), result.getJobCategoryId());
    }

    @Test
    void testCreateJobSubcategoryJobCategoryNotFound() {
        when(jobCategoryService.getJobCategoryById(1L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> jobSubCategoryService.createJobSubcategory(jobSubCategoryDto));
    } */
}
