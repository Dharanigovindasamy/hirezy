package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobPostDto;

import java.util.List;

/**
 * Interface for JobPostService  to handle  JobPost-related operation.
 * @author  Kishore
 *
 */
public interface JobPostService {
    List<JobPostDto> getAllJobs();
    JobPostDto getJobById(Long id);
    List<JobPostDto> searchJobsByFilters(String state, String city, String jobCategoryName,
                                          String jobSubcategoryName, String companyName,
                                         String companyType, String industryType);
}
