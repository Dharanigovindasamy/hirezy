package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.model.JobApplication;
import org.springframework.stereotype.Service;

@Service
public interface JobApplicationService {
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
      *     Getting job application by giving id
      *  @param id - job application id of the application
      * @return JobApplicationDto - the respective job application getting from id given
      * </p>
      *
      */
    JobApplicationDto getJobApplicationById(Long id);

    JobApplication applyJobByEmployee(Long employeeId, Long jobPostId);

    /**
     *
     * @param applicationId - jpb application id.
     * @param status  set new status to be Reviewed, Accpeted, rejected.
     * @return jobApplication -the respective job application getting form id given
     */

     JobApplicationDto updateApplicationStatus(Long applicationId, String status);

     List<JobApplicationDto> getJobApplicationByjobPostId(Long jobpostId);


}
