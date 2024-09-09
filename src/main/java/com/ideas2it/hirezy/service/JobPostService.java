package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.JobPost;

/**
 * <p>
 *     Interface for JobPostService to handle JobPost-related operations.
 * This interface defines the contract for services that manage job posts, including
 *  creation, retrieval, updating, deletion, and searching for job posts based on various filters.
 *  Implementations of this interface will provide the business logic for interacting with job posts.
 * </p>
 *
 * @author Kishore
 */
public interface JobPostService {
    /**
     * Retrieves all job posts available in the system.
     *
     * @return A list of JobPostDto objects representing all job posts.
     */
    List<JobPostDto> getAllJobs();

    /**
     * Retrieves a specific job post by its ID.
     *
     * @param id The ID of the job post to retrieve.
     * @return A JobPostDto object representing the job post with the specified ID.
     */
    JobPostDto getJobById(Long id);

    /**
     * Creates a new job post associated with a specific employer.
     *
     * @param employerId The ID of the employer creating the job post.
     * @param jobPostDto -{@link JobPostDto}A JobPostDto object containing the details of the job post to be created.
     * @return A JobPostDto object representing the newly created job post.
     */
    JobPostDto createJobPost(long employerId, JobPostDto jobPostDto);

    /**
     * Updates an existing job post by its ID.
     *
     * @param jobId The ID of the job post to update.
     * @param jobPostDto - {@link JobPostDto}A JobPostDto object containing the updated details of the job post.
     * @return A JobPostDto object representing the updated job post.
     */
    JobPostDto updateJobPost(Long jobId, JobPostDto jobPostDto);

    /**
     * Deletes a job post by its ID.
     *
     * @param jobId The ID of the job post to delete.
     */
    void deleteJobPost(Long jobId);

    /**
     * Retrieves all job posts created by a specific employer.
     *
     * @param employerId The ID of the employer whose job posts are to be retrieved.
     * @return A list of JobPostDto objects representing the job posts created by the specified employer.
     */
    List<JobPostDto> getAllJobPostsByEmployer(Long employerId);

    /**
     * Searches for job posts based on various filters such as location, job category,
     * subcategory, company name, company type, and industry type.
     *
     * @param state The state where the job is located (can be null).
     * @param city The city where the job is located (can be null).
     * @param jobCategoryName The name of the job category to filter by (can be null).
     * @param jobSubcategoryName The name of the job subcategory to filter by (can be null).
     * @param companyName The name of the company offering the job (can be null).
     * @param companyType The type of the company (e.g., Private, Government) to filter by (can be null).
     * @param industryType The industry type (e.g., IT, Manufacturing) to filter by (can be null).
     * @return A list of JobPostDto objects that match the specified filters.
     */
    List<JobPostDto> searchJobsByFilters(String state, String city, String jobCategoryName,
                                         String jobSubcategoryName, String companyName,
                                         String companyType, String industryType,Integer experience,List<String> keySkills);

    /**
     * Retrieve the job post is there present or not, after the retrieving apply for the job
     * @param jobPostId - Id of the jobPost
     * @return JobPost of the specific id
     */
    JobPost retrieveJobForApplication(long jobPostId);

    /**
     *  Auto-match jobposts with an employee's profile based on key-skills, city and experience.
     * @param employeeId employee whose profile will be matched with jobposts.
     * @return A list of JobPostDto that match with employee's profile.
     */
    List<JobPostDto> autoMatchJobPostsWithEmployee(Long employeeId);
}
