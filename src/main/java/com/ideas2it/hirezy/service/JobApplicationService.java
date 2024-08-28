package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.dto.JobApplicationDto;
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
    String removeJobApplicationForEmployee(Long id);


    /**
      * <p>
      *     Getting job application by giving id
      *  @param id - job application id of the application
      * @return JobApplicationDto - the respective job application getting from id given
      * </p>
      *
      */
    JobApplicationDto getJobApplicationById(Long id);

    /**
     *
     * @param applicationId - jpb application id.
     * @param status  set new status to be Reviewed, Accepted, rejected.
     * @return jobApplication -the respective job application getting form id given
     */

     JobApplicationDto updateApplicationStatus(Long applicationId, String status);

     List<JobApplicationDto> getJobApplicationByjobPostId(Long jobpostId);

    /**
     * This method is to assign job for the employee.
     * @param employeeId
     *     It is the id of the employee to be assigned job.
     * @param jobPostId
     *     It is id of the job to be assigned
     * @return String
     *     It is the message to the employee.
     */
     String applyJob(long employeeId, long jobPostId);

    /**
     * This method is to retrieve the jobs which was applied by the particular Employee.
     * @param employeeId
     *     It is the id of the employee to get their job.
     * @return List<JobApplicationDto>.
     *     It is list of jobs which was applies by the employee.
     */
     List<JobApplicationDto> retrieveEmployeeAppliedJobs(Long employeeId);
}
