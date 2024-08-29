package com.ideas2it.hirezy.repository;

import java.util.List;

import com.ideas2it.hirezy.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     This class used for job application details which can operate CRUD operations by Jpa repository
 * Repository interface for managing JobCategory entities.
 * Provides CRUD operations for JobCategory entities.
 * This interface extends CrudRepository to inherit basic CRUD methods.
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    /**
     * List of job applications under the job post
     * @param jobPostId - od of the job post
     * @return List of job applications under job post
     */
    List<JobApplication> findByJobPostId(Long jobPostId);

    /**
     * List of job applications under the job post
     *
     * @param employeeId - id of the employer
     *  @return List of job applications who posted the job
     */
    List<JobApplication> findByEmployeeId(Long employeeId);

}
