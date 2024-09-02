package com.ideas2it.hirezy.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.repository.JobSubCategoryRepository;

@ExtendWith(MockitoExtension.class)
public class JobSubCategoryServiceTest {

    @Mock
    private JobSubCategoryRepository jobSubCategoryRepository;

    @InjectMocks
    private JobSubCategoryServiceImpl jobSubCategoryService;
    @Mock
    private JobCategoryServiceImpl jobCategoryService;

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
    void testCreateJobSubcategorySuccess() {
        when(jobCategoryService.getJobCategoryById(jobSubCategoryDto.getJobCategoryId())).thenReturn(jobCategoryDto);
        when(jobSubCategoryRepository.save(any(JobSubCategory.class))).thenReturn(jobSubCategory);
        JobSubCategoryDto result = jobSubCategoryService.createJobSubcategory(jobSubCategoryDto);
        assertNotNull(result);
        assertEquals(jobSubCategoryDto.getJobCategoryId(), result.getJobCategoryId());
    }


    @Test
    void testJobCategoryNotFound() {
        when(jobCategoryService.getJobCategoryById(1L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> jobSubCategoryService.createJobSubcategory(jobSubCategoryDto));
    }

    @Test
    void testGetAllJobSubCategories() {
        when(jobSubCategoryRepository.findAll()).thenReturn(List.of(jobSubCategory));
        List<JobSubCategoryDto> response = jobSubCategoryService.getAllJobSubCategories();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void getJobSubCategoryById() {
        when(jobSubCategoryRepository.findById(jobSubCategoryDto.getJobCategoryId())).thenReturn(Optional.ofNullable(jobSubCategory));
        JobSubCategoryDto response = jobSubCategoryService.getJobSubCategoryById(jobSubCategoryDto.getId());
        assertNotNull(response);
        assertEquals(jobSubCategoryDto.getName(), response.getName());
    }

    @Test
    void getJobSubCategoryByIdFailure() {
        when(jobSubCategoryRepository.findById(jobSubCategoryDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobSubCategoryService.getJobSubCategoryById(jobSubCategoryDto.getId()));
    }

    @Test
    void testUpdateJobSubcategory() {
        when(jobSubCategoryRepository.findById(jobSubCategoryDto.getId())).thenReturn(Optional.ofNullable(jobSubCategory));
        when(jobCategoryService.getJobCategoryById(jobSubCategoryDto.getJobCategoryId())).thenReturn(jobCategoryDto);
        when(jobSubCategoryRepository.save(jobSubCategory)).thenReturn(jobSubCategory);
        JobSubCategoryDto response = jobSubCategoryService.updateJobSubcategory(jobSubCategoryDto.getId(), jobSubCategoryDto);
        assertNotNull(response);
        assertEquals(jobSubCategoryDto.getName(), response.getName());
    }

    @Test
    void testUpdateJobSubCategoryFailure() {
        when(jobSubCategoryRepository.findById(jobSubCategoryDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobSubCategoryService.updateJobSubcategory(jobSubCategoryDto.getId(), jobSubCategoryDto));
    }


    @Test
    void testDeleteJobSubcategorySuccess() {
        when(jobSubCategoryRepository.findById(jobSubCategoryDto.getId())).thenReturn(Optional.of(jobSubCategory));
        jobSubCategoryService.deleteJobSubcategory(1L);
        verify(jobSubCategoryRepository, times(1)).delete(jobSubCategory);
    }

    @Test
    void testDeleteJobSubcategoryNotFound() {
        when(jobSubCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobSubCategoryService.deleteJobSubcategory(1L));
    }
}
