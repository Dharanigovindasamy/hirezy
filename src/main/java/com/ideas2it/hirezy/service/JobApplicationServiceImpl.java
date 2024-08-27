package com.ideas2it.hirezy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ideas2it.hirezy.mapper.JobApplicationMapper;
import com.ideas2it.hirezy.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.repository.JobApplicationRepository;
import static com.ideas2it.hirezy.mapper.EmployeeMapper.mapDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployeeMapper.mapEntityToDto;
import static com.ideas2it.hirezy.mapper.JobApplicationMapper.mapToJobApplication;
import static com.ideas2it.hirezy.mapper.JobApplicationMapper.mapToJobApplicationDto;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPost;
import static com.ideas2it.hirezy.mapper.JobPostMapper.mapToJobPostDto;

/**
 * <p>
 *    This class used for business logic of job applicants profile like CRUD operations in job applications,etc..
 *  *   and convert the json data into http data
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService{
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private EmployeeService employeeService ;

    @Autowired
    private JobPostService jobPostService;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<JobApplicationDto> getAllJobApplications() {
        List<JobApplicationDto> jobApplicationDtos = new ArrayList<>();
        List<JobApplication> jobApplications = (List<JobApplication>) jobApplicationRepository.findAll();
        if (jobApplications.isEmpty()) {
            logger.warn("Empty job application details");
        } else {
            for(JobApplication jobApplication : jobApplications) {
                JobApplicationDto jobApplicationDto = mapToJobApplicationDto(jobApplication);
                jobApplicationDtos.add(jobApplicationDto);
            }
        }
        return jobApplicationDtos;
    }

    @Override
    public JobApplicationDto getJobApplicationById(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("job application not found" + id));
        if(null == jobApplication) {
            logger.warn("No job application under this job application id {}", id);
            return null;
        }
        return mapToJobApplicationDto(jobApplication);
    }


    @Override
    public void removeJobApplication(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found" + id));
        if (null == jobApplication) {
            logger.warn("No job Application found in id {}", id);
        }
        jobApplication.setActive(true);
        jobApplicationRepository.save(jobApplication);
        logger.info("Employee id deleted successfully {} ", id);
    }


    @Override
    public JobApplication applyJobByEmployee(Long employeeId, Long jobPostId) {
        EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
        Employee employee = mapDtoToEntity(employeeDto);
        JobPostDto jobPostDto = jobPostService.getJobById(jobPostId);
        JobPost jobPost = mapToJobPost(jobPostDto, Employer.builder().build());

        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
        if (jobPost == null) {
            throw new ResourceNotFoundException("Job post not found with ID: " + jobPostId);
        }
        JobApplication jobApplication = jobApplicationRepository.findByEmployeeAndJobPost(employee, jobPost);
        if (jobApplication == null) {
            jobApplication = JobApplication.builder()
                    .employee(employee)
                    .jobPost(jobPost)
                    .status("Applied")
                    .appliedDate(LocalDateTime.now())
                    .isActive(true)
                    .build();
        } else {
            logger.info("Application already exists for employee ID: " + employeeId + " and job post ID: " + jobPostId);
        }
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public JobApplicationDto updateApplicationStatus(Long applicationId, String status) {
        Optional<JobApplication> jobApplicationOptional = jobApplicationRepository.findById(applicationId);
        if (jobApplicationOptional.isPresent()) {
            JobApplication jobApplication = jobApplicationOptional.get();
            jobApplication.setStatus(status);
            jobApplicationRepository.save(jobApplication);
            return mapToJobApplicationDto(jobApplication);
        } else {
            throw new ResourceNotFoundException("Job application not found");
        }
    }

    @Override
    public List<JobApplicationDto> getJobApplicationByjobPostId(Long jobpostId) {
        return jobApplicationRepository.findByJobPostId(jobpostId)
                .stream()
                .map(JobApplicationMapper::mapToJobApplicationDto)
                .collect(Collectors.toList());
    }
}


