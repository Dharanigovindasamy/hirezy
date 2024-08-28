package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Data Transfer Object (DTO) for Job Category. This class is used to
 *     transfer job category data between processes.
 * </p>
 *
 * */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobCategoryDto {
    private long id;
    @NotBlank(message = "Job category name cannot be blank")
    private String name;
}
