package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.repository.JobPostRepository;
import com.ideas2it.hirezy.repository.JobPostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPostDto;

public class JobPostServiceImpl implements JobPostService {
    @Autowired
    private JobPostRepository jobPostRepository;

    @Override
    public List<JobPostDto> getAllJobs() {
        List<JobPost> jobPosts = jobPostRepository.findAll();
        List<JobPostDto> jobPostDtos = new ArrayList<>();
        for (JobPost jobPost : jobPosts) {
            jobPostDtos.add(mapToJobPostDto(jobPost));
        }
        return jobPostDtos;
    }

    @Override
    public JobPostDto getJobById(Long id) {
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(id);
            return mapToJobPostDto(jobPostOptional.get());

    }

    public List<JobPostDto> searchJobsByFilters(String state, String city, String jobCategoryName,
                                        String jobSubcategoryName, String companyName,
                                       String companyType, String industryType) {

        Specification<Object> specification = Specification.where(null)
                .and(JobPostSpecifications.hasState(state))
                .and(JobPostSpecifications.hasCity(city))
                .and(JobPostSpecifications.hasJobCategoryName(jobCategoryName))
                .and(JobPostSpecifications.hasJobSubcategoryName(jobSubcategoryName))
                .and(JobPostSpecifications.hasCompanyName(companyName))
                .and(JobPostSpecifications.hasCompanyType(companyType))
                .and(JobPostSpecifications.hasIndustryType(industryType));

        List<JobPost> jobs = jobPostRepository.findAll((Sort) specification);
        List<JobPostDto> jobPostDtos = new ArrayList<>();
        for (JobPost jobPost : jobs) {
            jobPostDtos.add(mapToJobPostDto(jobPost));
        }
        return jobPostDtos;
    }
}
