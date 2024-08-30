package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.repository.JobPostRepository;
import com.ideas2it.hirezy.util.JobPostSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class JobPostServiceImplTest {
    @Mock
    private JobPostRepository jobPostRepository;

    @Mock
    private JobPostSpecifications jobPostSpecifications;

    @InjectMocks
    private JobPostServiceImpl jobPostService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllJobs() {
    }

    @Test
    void getJobById() {
    }

    @Test
    void searchJobsByFilters() {
    }

    @Test
    void retrieveJobForApplication() {
    }

    @Test
    void createJobPost() {
    }

    @Test
    void updateJobPost() {
    }

    @Test
    void deleteJobPost() {
    }

    @Test
    void getAllJobPostsByEmployer() {
    }
}