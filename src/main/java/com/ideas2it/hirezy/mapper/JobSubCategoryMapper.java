package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.model.JobSubCategory;

import java.util.Objects;

/**
 * Mapper for converting between JobSubcategory entity and JobSubcategoryDTO using builder pattern.
 */
public class JobSubCategoryMapper {

    /**
     * <p>
     *     This method used for conversion of jobSubCategory to jobSubCategoryDto
     * </p>
     * @param jobSubCategory - jobSubCategory from the admin
     * @return JobSubCategoryDto - {@link JobSubCategoryDto} given to the user
     */
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

    /**
     * <p>
     *     This method used for conversion of jobSubCategory to jobSubCategoryDto
     * </p>
     * @param jobSubCategoryDto - {@link JobSubCategoryDto} given from the admin
     * @return JobSubCategory - JobSubCategory given to the user
     */
    public static JobSubCategory maptoJobSubCategory(JobSubCategoryDto jobSubCategoryDto) {
        return JobSubCategory.builder()
               .id(jobSubCategoryDto.getId())
                .name(jobSubCategoryDto.getName())
                .build();
    }
}
