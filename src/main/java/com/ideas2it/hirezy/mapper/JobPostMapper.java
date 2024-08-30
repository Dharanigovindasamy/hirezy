package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.model.Location;

/**
 * Mapper for converting between JobPost entity and JobPostDTO.
 */
public class JobPostMapper {

    /**
     * <p>
     *     This method used for conversion of jobPost to jobPostDto
     * </p>
     * @param jobPost - jobPost details from the employer
     * @return JobPostDto - {@link JobPostDto} given to the user
     */
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

        if(jobPost.getJobSubCategory() != null) {
            jobPostDtoBuilder.jobSubCategoryId(jobPost.getJobSubCategory().getId());
        }

        return jobPostDtoBuilder.build();
    }

    /**
     * <p>
     *       This method used for conversion of jobPostDto to jobPost
     * </p>
     * @param jobPostDto - {@link JobPostDto} from the user
     * @param employer - employer details
     * @return JobPost - JobPost given to the user
     */
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

        if(jobPostDto.getJobSubCategoryId() != null) {
            JobSubCategory jobSubCategory = JobSubCategory.builder()
                    .id(jobPostDto.getJobSubCategoryId())
                    .build();
            jobPostBuilder.jobSubCategory(jobSubCategory);
        }

        return jobPostBuilder.build();
    }

    /**
     * <p>
     *       This method used for conversion of jobPostDto to jobPost
     * </p>
     * @param jobPostDto - {@link JobPostDto} from the user
     * @return JobPost - JobPost given to the user
     */
    public static JobPost mapToJobPost(JobPostDto jobPostDto) {
        if (jobPostDto == null) {
            return null;
        }
        return JobPost.builder()
                .title(jobPostDto.getTitle())
                .jobDescription(jobPostDto.getJobDescription())
                .experience(jobPostDto.getExperience())
                .keySkills(jobPostDto.getKeySkills())
                .location(Location.builder()
                        .state(jobPostDto.getState())
                        .city(jobPostDto.getCity())
                        .build())
                .jobCategory(JobCategory.builder()
                        .id(jobPostDto.getJobCategoryId())
                        .build())
                .jobCategory(JobCategory.builder()
                        .id(jobPostDto.getJobSubCategoryId())
                        .build())
                .employer(Employer.builder()
                        .id(jobPostDto.getEmployerId())
                        .build())

                .build();
    }
}
