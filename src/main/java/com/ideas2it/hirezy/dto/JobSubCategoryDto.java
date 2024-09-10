package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Data Transfer Object (DTO) for Job Subcategory. This class is used to
 *     transfer job subcategory data between processes.
 * </p>
 * */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobSubCategoryDto {
    private Long id;
    @NotBlank(message = "Job subcategory name cannot be blank")
    private String name;
    @NotNull(message = "Job category ID cannot be null")
    private long jobCategoryId;
}
