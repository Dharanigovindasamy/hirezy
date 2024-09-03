package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.repository.JobCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobCategoryServiceTest {

    @Mock
    private JobCategoryRepository jobCategoryRepository;

    @InjectMocks
    private JobCategoryServiceImpl jobCategoryServiceImpl;

    private JobCategory jobCategory;
    private JobCategoryDto jobCategoryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        jobCategory = new JobCategory();
        jobCategory.setId(1L);
        jobCategory.setName("IT");

        jobCategoryDto = new JobCategoryDto();
        jobCategoryDto.setId(1L);
        jobCategoryDto.setName("IT");
    }

    @Test
    void testGetAllJobCategories() {
        when(jobCategoryRepository.findAll()).thenReturn(Arrays.asList(jobCategory));

        List<JobCategoryDto> jobCategoryDtos = jobCategoryServiceImpl.getAllJobCategories();

        assertEquals(1, jobCategoryDtos.size());
        assertEquals("IT", jobCategoryDtos.get(0).getName());
    }

    @Test
    void testGetJobCategoryById_Success() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.of(jobCategory));

        JobCategoryDto foundJobCategoryDto = jobCategoryServiceImpl.getJobCategoryById(1L);

        assertNotNull(foundJobCategoryDto);
        assertEquals("IT", foundJobCategoryDto.getName());
    }

    @Test
    void testGetJobCategoryById_NotFound() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            jobCategoryServiceImpl.getJobCategoryById(1L);
        });
        verify(jobCategoryRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateJobCategory_Success() {
        when(jobCategoryRepository.existsByName("IT")).thenReturn(false);
        when(jobCategoryRepository.save(any(JobCategory.class))).thenReturn(jobCategory);

        JobCategoryDto createdJobCategoryDto = jobCategoryServiceImpl.createJobCategory(jobCategoryDto);

        assertNotNull(createdJobCategoryDto);
        assertEquals("IT", createdJobCategoryDto.getName());
    }

    @Test
    void testCreateJobCategory_AlreadyExists() {
        when(jobCategoryRepository.existsByName("IT")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> {
            jobCategoryServiceImpl.createJobCategory(jobCategoryDto);
        });

        verify(jobCategoryRepository, times(1)).existsByName("IT");
        verify(jobCategoryRepository, times(0)).save(any(JobCategory.class));
    }

    @Test
    void testUpdateJobCategory_Success() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.of(jobCategory));
        when(jobCategoryRepository.save(any(JobCategory.class))).thenReturn(jobCategory);

        JobCategoryDto updatedJobCategoryDto = jobCategoryServiceImpl.updateJobCategory(1L, jobCategoryDto);

        assertNotNull(updatedJobCategoryDto);
        assertEquals("IT", updatedJobCategoryDto.getName());
    }

    @Test
    void testUpdateJobCategory_NotFound() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            jobCategoryServiceImpl.updateJobCategory(1L, jobCategoryDto);
        });

        verify(jobCategoryRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteJobCategory_Success() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.of(jobCategory));

        jobCategoryServiceImpl.deleteJobCategory(1L);

        verify(jobCategoryRepository, times(1)).findById(1L);
        verify(jobCategoryRepository, times(1)).delete(jobCategory);
    }

    @Test
    void testDeleteJobCategory_NotFound() {
        when(jobCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            jobCategoryServiceImpl.deleteJobCategory(1L);
        });

        verify(jobCategoryRepository, times(1)).findById(1L);
        verify(jobCategoryRepository, times(0)).delete(any(JobCategory.class));
    }
}