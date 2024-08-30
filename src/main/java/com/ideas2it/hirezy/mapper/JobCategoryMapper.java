package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.model.JobCategory;

/**
 * Mapper for converting between JobCategory entity and JobCategoryDTO.
 * @author kishore
 */
public class JobCategoryMapper {

    public static JobCategoryDto mapTojobCategoryDto(JobCategory jobCategory) {
        if (jobCategory == null) {
            return null;
        }

        return JobCategoryDto.builder()
                .id(jobCategory.getId())
                .name(jobCategory.getName())
                .build();
    }

    public static JobCategory mapToJobCategory(JobCategoryDto jobCategoryDto) {
        if (jobCategoryDto == null) {
            return null;
        }

        return JobCategory.builder()
                .id(jobCategoryDto.getId())
                .name(jobCategoryDto.getName())
                .build();
    }
}
