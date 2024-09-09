package com.ideas2it.hirezy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ideas2it.hirezy.exception.AccessDeniedException;
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

    private static final Logger logger = LogManager.getLogger(JobApplicationServiceImpl.class);

    @Override
    public List<JobApplicationDto> getAllJobApplications() {
        List<JobApplicationDto> jobApplicationDtos = new ArrayList<>();
        List<JobApplication> jobApplications = jobApplicationRepository.findByIsDeletedFalse();
        if (jobApplications.isEmpty()) {
            logger.warn("Empty job application details");
            throw new ResourceNotFoundException("Currently there is no job application");
        }
        for(JobApplication jobApplication : jobApplications) {
            JobApplicationDto jobApplicationDto = mapToJobApplicationDto(jobApplication);
            jobApplicationDtos.add(jobApplicationDto);
        }
        return jobApplicationDtos;
    }

    @Override
    public JobApplicationDto getJobApplicationById(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findByIdAndIsDeletedFalse(id);
        if(null == jobApplication) {
            logger.warn("No job application under this job application id {}", id);
            throw new ResourceNotFoundException("job application not found" + id);
        }
        return mapToJobApplicationDto(jobApplication);
    }

    @Override
    public String removeJobApplicationForEmployee(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findByIdAndIsDeletedFalse(id);
        if (null == jobApplication) {
            logger.warn("No job Application found in id {}", id);
            throw  new ResourceNotFoundException("Job application not found" + id);
        }
        jobApplication.setStatus(JobApplicationStatus.WITHDRAW);
        jobApplicationRepository.save(jobApplication);
        logger.info("Employee Withdraw application successfully {} ", id);
        return "Job application Withdraw application  Successfully";
    }

    @Override
    public JobApplicationDto updateApplicationStatus(Long applicationId, String status) {
        JobApplication jobApplication = jobApplicationRepository.findByIdAndIsDeletedFalse(applicationId);
        if(jobApplication == null) {
            throw new ResourceNotFoundException("There is no job application");
        }
        if(jobApplication.getStatus().equals(JobApplicationStatus.WITHDRAW) ||
                jobApplication.getStatus().equals(JobApplicationStatus.REJECTED)) {
             throw new AccessDeniedException("Employee already rejected or withdrew");
        }
        jobApplication.setStatus(JobApplicationStatus.valueOf(status));
        jobApplicationRepository.save(jobApplication);
        String employeeEmail = jobApplication.getEmployee().getUser().getEmailId();
        String subject = "Your Job Application Status Has Been Updated";
        String message = String.format("Dear %s,\n\nYour application status for the job post '%s' has been updated to '%s'.\n\nBest regards,\n '%s'",
                jobApplication.getEmployee().getName(), jobApplication.getJobPost().getTitle(), status,jobApplication.getJobPost().getEmployer().getCompanyName());

        emailService.sendEmail(employeeEmail, subject, message);
        return mapToJobApplicationDto(jobApplication);

    }

    @Override
    public List<JobApplicationDto> getJobApplicationByJobPostId(Long jobPostId) {
        return jobApplicationRepository.findByJobPostId(jobPostId)
                .stream()
                .map(JobApplicationMapper::mapToJobApplicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public String applyJob(long employeeId, long jobPostId) {
        Employee employee = employeeService.retrieveEmployeeForJobPost(employeeId);
        JobPost jobPost = jobPostService.retrieveJobForApplication(jobPostId);

        JobApplication jobApplication = JobApplication.builder()
                .employee(employee)
                .jobPost(jobPost)
                .appliedDate(LocalDateTime.now())
                .status(JobApplicationStatus.APPLIED)
                .build();
        jobApplicationRepository.save(jobApplication);
        return "Job Applied Successfully";
    }

    @Override
    public List<JobApplicationDto> retrieveEmployeeAppliedJobs(Long employeeId) {

        List<JobApplication> jobApplications = jobApplicationRepository.findByEmployeeId(employeeId);
        if(jobApplications.isEmpty()){
            throw new ResourceNotFoundException("The Employee has no job application" + employeeId);
        }
        List<JobApplicationDto> jobApplicationDto = new ArrayList<>();
        for (JobApplication jobApplication : jobApplications) {
            jobApplicationDto.add(mapToJobApplicationDto(jobApplication));
        }
        return  jobApplicationDto;
    }
}
