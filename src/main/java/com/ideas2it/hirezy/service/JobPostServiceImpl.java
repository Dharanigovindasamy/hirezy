package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.mapper.JobPostMapper;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.repository.JobPostRepository;
import com.ideas2it.hirezy.repository.JobPostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ideas2it.hirezy.mapper.EmployerMapper.convertDtoToEntity;
import static com.ideas2it.hirezy.mapper.JobCategoryMapper.mapToJobCategory;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPost;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPostDto;
import static com.ideas2it.hirezy.mapper.LocationMapper.mapToLocation;

@Service
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
    @Override
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

    @Override
    public JobPostDto createJobPost(JobPostDto jobPostDto) {
        Employer employer = convertDtoToEntity(jobPostDto.getEmployer());
        JobPost jobPost = mapToJobPost(jobPostDto);
        jobPost.setEmployer(employer);
        jobPost.setPostedDate(LocalDate.now());
        return mapToJobPostDto(jobPostRepository.save(jobPost));
    }

    @Override
    public JobPostDto updateJobPost(Long jobId, JobPostDto jobPostDto) {
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(jobId);
        if (jobPostOptional.isPresent()) {
            JobPost jobPost = jobPostOptional.get();
            jobPost.setTitle(jobPostDto.getTitle());
            jobPost.setJobDescription(jobPostDto.getJobDescription());
            jobPost.setExperience(jobPostDto.getExperience());
            jobPost.setLocation(mapToLocation(jobPostDto.getLocation()));
            jobPost.setJobCategory(mapToJobCategory(jobPostDto.getJobCategory()));
            return mapToJobPostDto(jobPostRepository.save(jobPost));
        }
        return null;
    }

    @Override
    public void deleteJobPost(Long jobId) {
        jobPostRepository.deleteById(jobId);
    }

    @Override
    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        List<JobPost> jobPosts = jobPostRepository.findByEmployerId(employerId);
        return jobPosts.stream()
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());
    }
}
