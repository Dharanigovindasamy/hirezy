package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.JobPostMapper;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.JobPostRepository;
import com.ideas2it.hirezy.repository.JobPostSpecifications;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    private EmployerService employerService;

    @Autowired
    private  LocationService locationService;

    @Autowired
    private  JobCategoryService jobCategoryService;

    private static final Logger logger = LogManager.getLogger(JobPostServiceImpl.class);

    @Override
    public List<JobPostDto> getAllJobs() {
        logger.info("Fetching all job posts");
        List<JobPost> jobPosts = jobPostRepository.findAll();
        List<JobPostDto> jobPostDtos = new ArrayList<>();
        for (JobPost jobPost : jobPosts) {
            jobPostDtos.add(mapToJobPostDto(jobPost));
        }
        logger.info("Total job posts fetched: {}", jobPostDtos.size());
        return jobPostDtos;
    }

    @Override
    public JobPostDto getJobById(Long id) {
        logger.info("Fetching job post by ID: {}", id);
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(id);
        if (jobPostOptional.isPresent()) {
            logger.info("Job post found with ID: {}", id);
            return mapToJobPostDto(jobPostOptional.get());
        } else {
            logger.error("Job post not found with ID: {}", id);
            throw new ResourceNotFoundException("JobPost not found with ID " + id);
        }

    }
    @Override
    public List<JobPostDto> searchJobsByFilters(String state, String city, String jobCategoryName,
                                                String jobSubcategoryName, String companyName,
                                                String companyType, String industryType,Integer experience,List<String> keySkills) {
        Specification<JobPost> specification = Specification.where(JobPostSpecifications.hasState(state))
                .and(JobPostSpecifications.hasCity(city))
                .and(JobPostSpecifications.hasJobCategoryName(jobCategoryName))
                .and(JobPostSpecifications.hasJobSubcategoryName(jobSubcategoryName))
                .and(JobPostSpecifications.hasCompanyName(companyName))
                .and(JobPostSpecifications.hasCompanyType(companyType))
                .and(JobPostSpecifications.hasIndustryType(industryType))
                .and(JobPostSpecifications.hasExperience(experience))
                .and(JobPostSpecifications.hasKeySkills(keySkills));

        List<JobPost> jobs = jobPostRepository.findAll(specification);
        List<JobPostDto> jobPostDtos = jobs.stream()
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());

        logger.info("Total job posts found: {}", jobPostDtos.size());
        return jobPostDtos;
    }

    @Override
    public JobPost retrieveJobForApplication(long jobPostId) {

        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new ResourceNotFoundException("job application not found" + jobPostId));
         return jobPost;

    }

    @Override
    public JobPostDto createJobPost(long employerId, JobPostDto jobPostDto) {
        logger.info("Creating job post for employer ID: {}", employerId);
        EmployerDto employerDto = employerService.getEmployerById(employerId);
        if (employerDto == null) {
            logger.error("Employer not found with ID: {}", employerId);
            throw new ResourceNotFoundException("Employer not found with id " + employerId);
        }
        Employer employer = convertDtoToEntity(employerDto);
        employer.setId(employerId);
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobPostDto.getJobCategoryId());
        if (jobCategoryDto == null) {
            logger.error("Job category not found with ID: {}", jobPostDto.getJobCategoryId());
            throw new ResourceNotFoundException("Job Category not found");
        }
        JobCategory jobCategory = mapToJobCategory(jobCategoryDto);
        Location location = locationService.findOrCreateLocation(jobPostDto.getState(), jobPostDto.getCity());
        JobPost jobPost = mapToJobPost(jobPostDto, employer);
        jobPost.setJobCategory(jobCategory);
        jobPost.setLocation(location);
        if (jobPost.getPostedDate() == null) {
            jobPost.setPostedDate(LocalDate.now());
        }
        jobPost = jobPostRepository.save(jobPost);
        logger.info("Job post created successfully with ID: {}", jobPost.getId());
        return mapToJobPostDto(jobPost);
    }

    @Override
    public JobPostDto updateJobPost(Long jobId, JobPostDto jobPostDto) {
        logger.info("Updating job post with ID: {}", jobId);
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(jobId);
        if (jobPostOptional.isPresent()) {
            JobPost jobPost = jobPostOptional.get();
            jobPost.setTitle(jobPostDto.getTitle());
            jobPost.setJobDescription(jobPostDto.getJobDescription());
            JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobPostDto.getJobCategoryId());
            if (jobCategoryDto == null) {
                logger.error("Job category not found with ID : {}", jobPostDto.getJobCategoryId());
                throw new ResourceNotFoundException("Job Category not found");
            }
            JobCategory jobCategory = mapToJobCategory(jobCategoryDto);
            jobPost.setJobCategory(jobCategory);
            Location location = locationService.findOrCreateLocation(jobPostDto.getState(), jobPostDto.getCity());
            jobPost.setLocation(location);
            jobPost = jobPostRepository.save(jobPost);
            logger.info("Job post updated successfully with ID: {}", jobId);
            return mapToJobPostDto(jobPost);
        } else {
            logger.error(" Job post not found with ID: {}", jobId);
            throw new ResourceNotFoundException("JobPost not found with id " + jobId);
        }
    }

    @Override
    public void deleteJobPost(Long jobId) {
        logger.info("Deleting job post with ID: {}", jobId);
        jobPostRepository.deleteById(jobId);
        logger.info("Job post deleted successfully with ID: {}", jobId);
    }

    @Override
    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        logger.info("Fetching all job posts for employer ID: {}", employerId);
        List<JobPost> jobPosts = jobPostRepository.findByEmployerId(employerId);
        List<JobPostDto> jobPostDtos = jobPosts.stream()
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());
        logger.info("Total job posts found for employer ID {}: {}", employerId, jobPostDtos.size());
        return jobPostDtos;
    }
}
