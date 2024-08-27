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

        JobPostDto.JobPostDtoBuilder jobPostDtoBuilder = JobPostDto.builder()
                .id(jobPost.getId())
                .title(jobPost.getTitle())
                .jobDescription(jobPost.getJobDescription())
                .experience(jobPost.getExperience())
                .postedDate(jobPost.getPostedDate())
                .keySkills(jobPost.getKeySkills())
                .employerId(jobPost.getEmployer() != null ? jobPost.getEmployer().getId() : null);

        if (jobPost.getLocation() != null) {
            jobPostDtoBuilder.state(jobPost.getLocation().getState())
                    .city(jobPost.getLocation().getCity());
        }

        if (jobPost.getJobCategory() != null) {
            jobPostDtoBuilder.jobCategoryId(jobPost.getJobCategory().getId());
        }

        return jobPostDtoBuilder.build();
    }

    public static JobPost mapToJobPost(JobPostDto jobPostDto, Employer employer) {
        if (jobPostDto == null) {
            return null;
        }

        JobPost.JobPostBuilder jobPostBuilder = JobPost.builder()
                .id(jobPostDto.getId())
                .title(jobPostDto.getTitle())
                .jobDescription(jobPostDto.getJobDescription())
                .experience(jobPostDto.getExperience())
                .postedDate(jobPostDto.getPostedDate())
                .keySkills(jobPostDto.getKeySkills())
                .employer(employer);

        if (jobPostDto.getState() != null || jobPostDto.getCity() != null) {
            Location location = Location.builder()
                    .state(jobPostDto.getState())
                    .city(jobPostDto.getCity())
                    .build();
            jobPostBuilder.location(location);
        }

        if (jobPostDto.getJobCategoryId() != null) {
            JobCategory jobCategory = JobCategory.builder()
                    .id(jobPostDto.getJobCategoryId())
                    .build();
            jobPostBuilder.jobCategory(jobCategory);
        }

        return jobPostBuilder.build();
    }
}
