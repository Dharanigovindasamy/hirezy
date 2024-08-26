package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *     The `EmployerService` interface provides an abstraction for employer-related
 *     operations in the application. It defines methods to manage employers, including
 *     creating, updating, retrieving, and deleting employer records. Additionally, this
 *     service handles operations related to job posts associated with employers, such as
 *     updating, deleting, and retrieving all job posts by a specific employer.
 * </p>
 **/
@Service
public interface EmployerService {
     /**
      * This method will create a company in the portal
      * @param employerDto
      */
     EmployerDto createEmployer(EmployerDto employerDto);

     /**
      *This method gets all the companies that are present
      */
     public List<EmployerDto> getAllEmployer();

     /**
      * This method is used to delete an company from the portal
      * @param id
      */
     public void removeEmployer(int id);

     /**
      * This method is used to alter the company details in the portal
      * @param id
      * @param employerDto
      * @return
      */
     public EmployerDto updateEmployer(int id, EmployerDto employerDto);

     /**
      * This method is used to get the company based on the id
      * @param id
      * @return
      */
     EmployerDto getEmployerById(long id);

     /**
      * <p>
      *     Updates a job post associated with a specific employer. This method updates the
      *     job post details based on the provided employer ID, job ID, and `JobPostDto`.
      * </p>
      *
      * @param employerId The unique identifier of the employer.
      * @param jobId The unique identifier of the job post to be updated.
      * @param jobPostDto The data transfer object containing the updated job post details.
      * @return The updated `JobPostDto` reflecting the changes made.
      */
     JobPostDto updateJobPost(long employerId, long jobId, JobPostDto jobPostDto);

     /**
      * <p>
      *     Deletes a job post associated with a specific employer based on the provided
      *     job ID. This method removes the job post record from the job post repository.
      * </p>
      *
      * @param jobId The unique identifier of the job post to be deleted.
      */
     void deleteJobPost(Long jobId);

     /**
      * <p>
      *     Retrieves a list of all job posts associated with a specific employer.
      *     This method returns all job posts linked to the given employer ID.
      * </p>
      *
      * @param employerId The unique identifier of the employer.
      * @return A list of `JobPostDto` representing all job posts for the specified employer.
      */
     List<JobPostDto> getAllJobPostsByEmployer(Long employerId);
}
