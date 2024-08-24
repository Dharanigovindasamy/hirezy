package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *     This class manages job post performance like adding, retrieve, update and delete operations
 * </p>
 *
 *
 */
@Controller
@RequestMapping("api/v1/jobPosts")
public class JobPostController {
    @Autowired
    private JobPostService jobPostService;

    @GetMapping
    public ResponseEntity<List<JobPostDto>> getAllJobs() {
        List<JobPostDto> jobs = jobPostService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostDto> getJobById(@PathVariable Long id) {
        JobPostDto jobPost = jobPostService.getJobById(id);
        return jobPost != null ? ResponseEntity.ok(jobPost) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostDto>> searchJobs(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String jobCategoryName,
            @RequestParam(required = false) String jobSubcategoryName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String companyType,
            @RequestParam(required = false) String industryType) {

        List<JobPostDto> jobs = jobPostService.searchJobsByFilters(state, city, jobCategoryName,
                jobSubcategoryName, companyName,
                companyType, industryType);
        return ResponseEntity.ok(jobs);
    }
}
