package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobApplication;
import com.ideas2it.hirezy.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    List<JobApplication> findByJobPostId(Long jobPostId);

    List<JobApplication> findByEmployeeId(Long employeeId);

}
