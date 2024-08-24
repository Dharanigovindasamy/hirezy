package com.ideas2it.hirezy.repository;


import com.ideas2it.hirezy.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *<p>This class is the interface to JPA repository and acts as the dao for CRUD
 * operations in the dao
 * </p>
@Author Audhithiyah
* @Version v1
 */
@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByEmployerId(Long employerId);
}