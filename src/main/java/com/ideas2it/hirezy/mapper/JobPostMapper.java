package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.model.Location;

/**
 * Mapper for converting between JobPost entity and JobPostDTO.
 */
public class JobPostMapper {
    public static JobPostDto mapToJobPostDto(JobPost jobPost) {
        if (jobPost == null) {
            return null;
        }

        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setId(jobPost.getId());
        jobPostDto.setTitle(jobPost.getTitle());
        jobPostDto.setJobDescription(jobPost.getJobDescription());
        jobPostDto.setPostedDate(jobPost.getPostedDate());
        jobPostDto.setEmployerId(jobPostDto.getEmployerId());

        if (jobPost.getLocation() != null) {
            jobPostDto.setState(jobPost.getLocation().getState());
            jobPostDto.setCity(jobPost.getLocation().getCity());
        }

        jobPostDto.setKeySkills(jobPost.getKeySkills());

        if (jobPost.getJobCategory() != null) {
            jobPostDto.setJobCategoryId(jobPost.getJobCategory().getId());
        }

        return jobPostDto;
    }

    public static JobPost mapToJobPost(JobPostDto jobPostDto, Employer employer) {
        if (jobPostDto == null) {
            return null;
        }

        JobPost jobPost = new JobPost();
        jobPost.setId(jobPostDto.getId());
        jobPost.setTitle(jobPostDto.getTitle());
        jobPost.setJobDescription(jobPostDto.getJobDescription());
        jobPost.setPostedDate(jobPostDto.getPostedDate());

        if (jobPostDto.getState() != null || jobPostDto.getCity() != null) {
            Location location = new Location();
            location.setState(jobPostDto.getState());
            location.setCity(jobPostDto.getCity());
            jobPost.setLocation(location);
        }

        jobPost.setKeySkills(jobPostDto.getKeySkills());

        if (jobPostDto.getJobCategoryId() != null) {
            JobCategory jobCategory = new JobCategory();
            jobCategory.setId(jobPostDto.getJobCategoryId());
            jobPost.setJobCategory(jobCategory);
        }
        jobPost.setEmployer(employer);

        return jobPost;
    }
}
