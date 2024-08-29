package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.service.JobPostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * This class handles the operations related to job posts, including adding, retrieving,
 * updating, and deleting job posts.
 * </p>
 * @author kishorekumar.n
 */
@Controller
@RequestMapping("api/v1/jobposts")
public class JobPostController {
    @Autowired
    private JobPostService jobPostService;

    private static final Logger logger = LogManager.getLogger(JobPostController.class);

    /**
     * <p>
     * Retrieves all job posts from the database.
     * </p>
     *
     * @return ResponseEntity containing the list of all job posts.
     */
    @GetMapping
    public ResponseEntity<List<JobPostDto>> getAllJobs() {
        logger.info("Fetching all job posts");
        List<JobPostDto> jobs = jobPostService.getAllJobs();
        logger.info("Total job posts retrieved: {}", jobs.size());
        return ResponseEntity.ok(jobs);
    }

    /**
     * <p>
     * Retrieves a specific job post by its unique identifier.
     * </p>
     *
     * @param id - unique identifier of the job post.
     * @return ResponseEntity containing the job post if found, otherwise a 404 status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPostDto> getJobById(@PathVariable Long id) {
        logger.info("Fetching job post with ID: {}", id);
        JobPostDto jobPost = jobPostService.getJobById(id);
        if (jobPost != null) {
            logger.info("Job post found with ID: {}", id);
            return ResponseEntity.ok(jobPost);
        } else {
            logger.warn("Job post not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * <p>
     * Searches for job posts based on various filters such as state, city, job category,
     * job subcategory, company name, company type, and industry type.
     * </p>
     *
     * @param state - state where the job is located (optional).
     * @param city - city where the job is located (optional).
     * @param jobCategoryName - name of the job category (optional).
     * @param jobSubcategoryName - name of the job subcategory (optional).
     * @param companyName - name of the company offering the job (optional).
     * @param companyType - type of the company offering the job (optional).
     * @param industryType - type of industry (optional).
     * @param experience - year of experience required (optional);
     * @return ResponseEntity containing the list of job posts that match the search criteria.
     */
    @PostMapping("/search")
    public ResponseEntity<List<JobPostDto>> searchJobs(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String jobCategoryName,
            @RequestParam(required = false) String jobSubcategoryName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String companyType,
            @RequestParam(required = false) String industryType,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) List<String> keySkills) {

        List<JobPostDto> jobs = jobPostService.searchJobsByFilters(state, city, jobCategoryName,
                jobSubcategoryName, companyName,
                companyType, industryType, experience, keySkills);
        logger.info("Total job posts found: {}", jobs.size());
        return ResponseEntity.ok(jobs);
    }
}
