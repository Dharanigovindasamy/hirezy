package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.JobApplication;
import com.ideas2it.hirezy.repository.JobApplicationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ideas2it.hirezy.mapper.JobApplicationMapper.mapToJobApplication;
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

        private static final Logger logger = LogManager.getLogger();

        @Override
        public JobApplicationDto addJobApplication(JobApplicationDto jobApplicationDto) {
            JobApplication jobApplication = mapToJobApplication(jobApplicationDto);
            jobApplicationDto = mapToJobApplicationDto(jobApplicationRepository.save(jobApplication));
            return jobApplicationDto;
        }

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
        public JobApplicationDto updateJobApplication(JobApplicationDto jobApplicationDto) {
            JobApplicationDto finalJobApplicationDto = jobApplicationDto;
            JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job application not found" + finalJobApplicationDto.getId()));
            if(null == jobApplication) {
                logger.warn("No job application under in this id {}", jobApplicationDto.getId());
                return null;
            } else {
                jobApplication.setStatus(jobApplicationDto.getStatus());
                jobApplication.setAppliedDate(jobApplicationDto.getAppliedDate());
                jobApplicationDto = mapToJobApplicationDto(jobApplicationRepository.save(jobApplication));
                logger.info("Job application details updated successfully {}", jobApplicationDto.getId());
            }
            return jobApplicationDto;
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

    public JobApplication updateJobApplicationStatus(Long jobApplicationId, String newStatus) {
        Optional<JobApplication> optionalJobApplication = jobApplicationRepository.findById(jobApplicationId);
        if (optionalJobApplication.isPresent()) {
            JobApplication jobApplication = optionalJobApplication.get();
            jobApplication.setStatus(newStatus);
            return jobApplicationRepository.save(jobApplication);
        } else {
            throw new RuntimeException("Job Application not found with id: " + jobApplicationId);
        }
    }
}
