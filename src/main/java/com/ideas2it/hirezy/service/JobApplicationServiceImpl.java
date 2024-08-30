package com.ideas2it.hirezy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ideas2it.hirezy.model.enums.JobApplicationStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobApplication;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.mapper.JobApplicationMapper;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.repository.JobApplicationRepository;
import static com.ideas2it.hirezy.mapper.JobApplicationMapper.mapToJobApplicationDto;


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

    @Autowired
    private EmailService emailService;

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
    public String removeJobApplicationForEmployee(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found" + id));
        if (null == jobApplication) {
            logger.warn("No job Application found in id {}", id);
        }
        jobApplication.setDeleted(true);
        jobApplicationRepository.save(jobApplication);
        logger.info("Employee id deleted successfully {} ", id);
        return "Job application Deleted Successfully";
    }

    @Override
    public JobApplicationDto updateApplicationStatus(Long applicationId, String status) {
        Optional<JobApplication> jobApplicationOptional = jobApplicationRepository.findById(applicationId);
        if (jobApplicationOptional.isPresent()) {
            JobApplication jobApplication = jobApplicationOptional.get();
            jobApplication.setStatus(JobApplicationStatus.valueOf(status));
            jobApplicationRepository.save(jobApplication);
            String employeeEmail = jobApplication.getEmployee().getUser().getEmailId();
            String subject = "Your Job Application Status Has Been Updated";
            String message = String.format("Dear %s,\n\nYour application status for the job post '%s' has been updated to '%s'.\n\nBest regards,\n '%s'",
                    jobApplication.getEmployee().getName(), jobApplication.getJobPost().getTitle(), status,jobApplication.getJobPost().getEmployer().getCompanyName());

            emailService.sendEmail(employeeEmail, subject, message);
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

    @Override
    public String applyJob(long employeeId, long jobPostId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobPostId)
                .orElseThrow(() -> new ResourceNotFoundException("The Job Post does not found" + jobPostId));
        Employee employee = employeeService.retrieveEmployeeForJobPost(employeeId);
        jobApplication.setEmployee(employee);
        JobPost jobPost = jobPostService.retrieveJobForApplication(jobPostId);
        jobApplication.setJobPost(jobPost);
        jobApplication.setAppliedDate(LocalDateTime.now());
        jobApplication.setStatus(JobApplicationStatus.APPLIED);
        jobApplicationRepository.save(jobApplication);
        return "Job Applied Successfully";
    }

    @Override
    public List<JobApplicationDto> retrieveEmployeeAppliedJobs(Long employeeId) {
        List<JobApplicationDto> jobApplicationDto = new ArrayList<>();
        for (JobApplication jobApplication : jobApplicationRepository.findByEmployeeId(employeeId)){
            if(null != jobApplication) {
                jobApplicationDto.add(mapToJobApplicationDto(jobApplication));
            }
            throw  new ResourceNotFoundException("The Employee has no job application" + employeeId);
        }
        return  jobApplicationDto;
    }
}


