package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.model.JobPost;

import static com.ideas2it.hirezy.mapper.EmployerMapper.ConvertDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployerMapper.ConvertEntityToDto;
import static com.ideas2it.hirezy.mapper.JobCategoryMapper.mapToJobCategory;
import static com.ideas2it.hirezy.mapper.JobCategoryMapper.mapTojobCategoryDto;
import static com.ideas2it.hirezy.mapper.LocationMapper.mapToLocation;
import static com.ideas2it.hirezy.mapper.LocationMapper.mapToLocationDto;
/**
 * Mapper for converting between JobPost entity and JobPostDTO.
 */
public class JobPostMapper {
    public static JobPostDto mapToJobPostDto(JobPost jobPost) {
        if (jobPost == null) {
            return null;
        }

        return JobPostDto.builder()
                .id(jobPost.getId())
                .title(jobPost.getTitle())
                .postedDate(jobPost.getPostedDate())
                .jobDescription(jobPost.getJobDescription())
                .experience(jobPost.getExperience())
                .location(mapToLocationDto(jobPost.getLocation()))
                .jobCategory(jobPost.getJobCategory() != null ? mapTojobCategoryDto(jobPost.getJobCategory()) : null)
                .employer(jobPost.getEmployer() != null ? ConvertEntityToDto(jobPost.getEmployer()) : null)
                .build();
    }

    public static JobPost mapToJobPost(JobPostDto jobPostDto) {
        if (jobPostDto == null) {
            return null;
        }

        return JobPost.builder()
                .id(jobPostDto.getId())
                .title(jobPostDto.getTitle())
                .postedDate(jobPostDto.getPostedDate())
                .jobDescription(jobPostDto.getJobDescription())
                .experience(jobPostDto.getExperience())
                .location(mapToLocation(jobPostDto.getLocation()))
                .jobCategory(jobPostDto.getJobCategory() != null ? mapToJobCategory(jobPostDto.getJobCategory()) : null)
                .employer(jobPostDto.getEmployer() != null ? ConvertDtoToEntity(jobPostDto.getEmployer()) : null)
                .build();
    }
}
