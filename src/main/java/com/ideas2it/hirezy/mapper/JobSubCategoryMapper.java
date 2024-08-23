package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.model.JobSubCategory;

/**
 * Mapper for converting between JobSubcategory entity and JobSubcategoryDTO using builder pattern.
 */
public class JobSubCategoryMapper {

    public static JobSubCategoryDto maptoJobSubCategoryDto(JobSubCategory jobSubCategory) {
        if (jobSubCategory == null) {
            return null;
        }
        return JobSubCategoryDto.builder()
                .id(jobSubCategory.getId())
                .name(jobSubCategory.getName())
                .jobCategoryId(jobSubCategory.getJobCategory().getId())
                .build();
    }

    public static JobSubCategory maptoJobSubCategory(JobSubCategoryDto jobSubCategoryDto) {
        if (jobSubCategoryDto == null) {
            return null;
        }
        return JobSubCategory.builder()
                .id(jobSubCategoryDto.getId())
                .name(jobSubCategoryDto.getName())
                .build();
    }
}
