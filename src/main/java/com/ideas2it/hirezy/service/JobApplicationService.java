package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.model.JobApplication;
import org.springframework.stereotype.Service;

@Service
public interface JobApplicationService {
     /**
      * <p>
      *
      *    This method will add job application when employee apply for the job
      * @param jobApplicationDto - jobApplicationDto from the user
      * @return JobApplicationDto - JobApplicationDto after storing into the entity table
      * </p>>
      */
     JobApplicationDto addJobApplication(JobApplicationDto jobApplicationDto);

     /**
      * <p>
      *     This method gets all the job applications which there exists
      * @return List<JobApplicationDto> - list of job applications
      * </p>
      */
     List<JobApplicationDto> getAllJobApplications();

     /**
      * <p>
      *     This method can delete the job application by giving id
      *  @param id - job application id which want to be deleted
      * </p>
      *
      */
     void removeJobApplication(Long id);

     /**
      * <p>
      *     This method is used to update the job application status
      * @param jobApplicationDto - the application data want to be updated
      * @return JobApplicationDto - the updated job application data
      * </p>
      */
      JobApplicationDto updateJobApplication(JobApplicationDto jobApplicationDto);

     /**
      * <p>
      *     Getting job application by giving id
      *  @param id - job application id of the application
      * @return JobApplicationDto - the respective job application getting from id given
      * </p>
      *
      */
      JobApplicationDto getJobApplicationById(Long id);

    JobApplication updateJobApplicationStatus(Long jobApplicationId, String newStatus);
}
