package com.ideas2it.hirezy.repository;

import java.util.List;

import com.ideas2it.hirezy.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.JobPost;

/*
 *<p>This class is the interface to JPA repository and acts as the dao for CRUD
 * operations in the dao
 * </p>
* @Author Audhithiyah
* @Version v1
 */
@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long>, JpaSpecificationExecutor<JobPost> {

    /**
     * Find the job post posted by employer by giving employer id
     * @param employerId - id of the employer
     * @return lost of job post posted by employer
     */
    List<JobPost> findByEmployerIdAndIsDeletedFalse(Long employerId);

    /**
     * <p>
     *This method is returns employer that is soft deleted
     * @param jobId - id of the jobpost.
     * </p>
     */
    JobPost findByIsDeletedFalseAndId(Long jobId);

    /**
     * This method is to retrieve all the JobPost.
     * @return List<JobPost>
     *     It contains all the job post.
     */
    List<JobPost> findByIsDeletedFalse();
}