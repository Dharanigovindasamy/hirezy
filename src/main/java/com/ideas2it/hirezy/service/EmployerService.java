package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.model.Employer;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;


/**
 * <p>
 *     The `EmployerService` interface provides an abstraction for employer-related
 *     operations in the application. It defines methods to manage employers, including
 *     creating, updating, retrieving, and deleting employer records. Additionally, this
 *     service handles operations related to job posts associated with employers, such as
 *     updating, deleting, and retrieving all job posts by a specific employer.
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 **/
@Service
public interface EmployerService {
     /**
      * This method will create a company in the portal
      * @param employerDto {@link EmployerDto}
      *     It contains the employeeDto details to be created.
      * @return EmployerDto
      *     It is the created employeeDto in the Database.
      */
     EmployerDto createEmployer(EmployerDto employerDto);

     /**
      *This method retrieves all the companies that are present
      */
     public List<EmployerDto> getAllEmployers();

     /**
      * This method is used to delete an company from the portal
      *
      * @param employerId
      *     It is id of the employer to removed.
      */
     public void removeEmployer(long employerId);

     /**
      * This method is used to alter the company details in the portal
      * @param employerDto - {@link EmployerDto}
      *            It contains the employeeDto details to be created.
      * @return EmployerDto - EmployerDto received to user
      */
      EmployerDto updateEmployer(EmployerDto employerDto);

     /**
      * This method is used to get the company based on the id
      * @param id - id of the employer
      * @return EmployerDto - EmployerDto received to user
      */
     EmployerDto retrieveEmployerById(long id);

     /**
      * This method is to retrieve the employer for job post.
      * @param employerId
      *     It is the id of the employee to be retrieved.
      * @return Employer
      *     It contains all the employer details.
      */
     Employer retrieveEmployerForJobPost(long employerId);

     /**
      * <p>
      *     Updates a job post associated with a specific employer. This method updates the
      *     job post details based on the provided employer ID, job ID, and `JobPostDto`.
      * </p>
      *
      * @param jobId The unique identifier of the job post to be updated.
      * @param jobPostDto -{@link JobPostDto}The data transfer object containing the updated job post details.
      * @return The updated `JobPostDto` reflecting the changes made.
      */
     JobPostDto updateJobPost(JobPostDto jobPostDto);

     /**
      * <p>
      *     Deletes a job post associated with a specific employer based on the provided
      *     job ID. This method removes the job post record from the job post repository.
      * </p>
      *
      * @param jobId The unique identifier of the job post to be deleted.
      */
     void deleteJobPost(long jobId);

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

     /**
      * Retrieves the count of active employers.
      * @return the number of active employers
      */
     Long countActiveEmployers();

     /**
      * Retrieves the count of deleted employers.
      * @return the number of deleted employers.
      */
     Long countDeletedEmployers();
}

