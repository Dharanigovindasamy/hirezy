package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobSubCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.JobPostMapper;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.JobPostRepository;
import com.ideas2it.hirezy.util.JobPostSpecifications;

import static com.ideas2it.hirezy.mapper.EmployeeMapper.mapDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployerMapper.convertDtoToEntity;
import static com.ideas2it.hirezy.mapper.JobCategoryMapper.mapToJobCategory;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPost;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPostDto;
import static com.ideas2it.hirezy.mapper.JobSubCategoryMapper.maptoJobSubCategory;

/**
 * This class used for job post functionalities lke adding , retrieving, updating nd deleting
 * @author kishore
 */
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

    @Autowired
    private JobSubCategoryService jobSubCategoryService;

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LogManager.getLogger(JobPostServiceImpl.class);

    @Override
    public List<JobPostDto> getAllJobs() {
        logger.info("Fetching all job posts");
        List<JobPost> jobPosts = jobPostRepository.findByIsDeletedFalse();
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
        Optional<JobPost> jobPostOptional = jobPostRepository.findByIdAndIsDeletedFalse(id);
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
                .filter(job -> !job.isDeleted())
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());

        logger.info("Total job posts found: {}", jobPostDtos.size());
        return jobPostDtos;
    }

    @Override
    public JobPost retrieveJobForApplication(long jobPostId) {
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new ResourceNotFoundException("job application not found {}" + jobPostId));
         return jobPost;

    }

    @Override
    public JobPostDto createJobPost(long employerId, JobPostDto jobPostDto) {
        logger.info("Creating job post for employer ID: {}", employerId);
        Employer employer = employerService.retrieveEmployerForJobPost(employerId);
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobPostDto.getJobCategoryId());
        if (jobCategoryDto == null) {
            logger.error("Job category not found with ID: {}", jobPostDto.getJobCategoryId());
            throw new ResourceNotFoundException("Job Category not found");
        }
        JobCategory jobCategory = mapToJobCategory(jobCategoryDto);
        JobSubCategoryDto jobSubCategoryDto = jobSubCategoryService.getJobSubCategoryById(jobPostDto.getJobSubCategoryId());
        JobSubCategory jobSubCategory = maptoJobSubCategory(jobSubCategoryDto);
        Location location = locationService.findOrCreateLocation(jobPostDto.getState(), jobPostDto.getCity());
        JobPost jobPost = mapToJobPost(jobPostDto);
        jobPost.setJobCategory(jobCategory);
        jobPost.setJobSubCategory(jobSubCategory);
        jobPost.setLocation(location);
        jobPost.setEmployer(employer);
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
        if(!jobPostRepository.existsById(jobId)) {
            logger.error(" Job post not found with ID: {}", jobId);
            throw new ResourceNotFoundException("JobPost not found with id " + jobId);
        }

        JobPost jobPost = mapToJobPost(jobPostDto);
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobPostDto.getJobCategoryId());
        JobCategory jobCategory = mapToJobCategory(jobCategoryDto);
        jobPost.setJobCategory(jobCategory);

        JobSubCategoryDto jobSubCategoryDto = jobSubCategoryService.getJobSubCategoryById(jobPostDto.getJobSubCategoryId());
        JobSubCategory jobSubCategory = maptoJobSubCategory(jobSubCategoryDto);
        jobPost.setJobSubCategory(jobSubCategory);

        Location location = locationService.findOrCreateLocation(jobPostDto.getState(), jobPostDto.getCity());
        jobPost.setLocation(location);
        Employer employer = employerService.retrieveEmployerForJobPost(jobPostDto.getEmployerId());
        jobPost.setEmployer(employer);
        jobPost = jobPostRepository.save(jobPost);
        logger.info("Job post updated successfully with ID: {}", jobId);
        return mapToJobPostDto(jobPost);
    }

    @Override
    public void deleteJobPost(Long jobId) {
        logger.info("Removing JobPost with ID: {}", jobId);
        JobPost jobPost = jobPostRepository.findByIsDeletedFalseAndId(jobId);
        if (jobPost == null) {
            logger.warn("JobPost not found with ID: {}", jobId);
            throw new ResourceNotFoundException("JobPost not found with ID: " + jobId);
        }
        jobPost.setDeleted(true);
        jobPostRepository.save(jobPost);
        logger.info("Employer with ID: {} has been marked as deleted", jobId);

    }


    @Override
    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        logger.info("Fetching all job posts for employer ID: {}", employerId);
        List<JobPost> jobPosts = jobPostRepository.findByEmployerIdAndIsDeletedFalse(employerId);

        List<JobPostDto> jobPostDtos = jobPosts.stream()
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());
        logger.info("Total job posts found for employer ID {}: {}", employerId, jobPostDtos.size());
        return jobPostDtos;
    }

    @Override
    public List<JobPostDto> autoMatchJobPostsWithEmployee(Long employeeId) {
        EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
        Employee employee = mapDtoToEntity(employeeDto);
        List<String> employeeSkills = employee.getKeySkills();
        List<JobPost> matchingJobs = jobPostRepository.findAll().stream()
                .filter(jobPost -> matchesEmployeeProfile(jobPost, employeeSkills, employee.getCity(), employee.getYearOfExperience()))
                .toList();

        return matchingJobs.stream()
                .filter(job -> !job.isDeleted())
                .map(JobPostMapper::mapToJobPostDto)
                .collect(Collectors.toList());
    }

    /**
     *  Helper method if the job post match with an employee profile.
     * @param jobPost The job post compare with employee profile.
     * @param employeeSkills The list of Key Skill  that the employee possesses.
     * @param employeeCity The city where the employee is located.
     * @param employeeExperience The year of experience employee has.
     * @return true if the job post matches the employee profile any of the criteria : otherwise false
     */
    private boolean matchesEmployeeProfile(JobPost jobPost, List<String> employeeSkills, String employeeCity, int employeeExperience) {
        boolean skillMatch = jobPost.getKeySkills().stream()
                .anyMatch(employeeSkills::contains);
        boolean locationMatch = jobPost.getLocation().getCity().equalsIgnoreCase(employeeCity);
        boolean experienceMatch = jobPost.getExperience() <= employeeExperience;
        return skillMatch || locationMatch || experienceMatch;
    }
}
