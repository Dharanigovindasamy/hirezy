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
    JobPostDto createJobPost(JobPostDto jobPostDto);
    JobPostDto updateJobPost(Long jobId, JobPostDto jobPostDto);
    void deleteJobPost(Long jobId);
    List<JobPostDto> getAllJobPostsByEmployer(Long employerId);
    List<JobPostDto> searchJobsByFilters(String state, String city, String jobCategoryName,
                                          String jobSubcategoryName, String companyName,
                                         String companyType, String industryType);
}
