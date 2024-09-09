package com.ideas2it.hirezy.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     The class defines the job post details that send and receive as json format
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostDto {
    private long id;

    @NotBlank(message = "Job post title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String jobDescription;

    @NotNull(message = "Experience is mandatory")
    @Size(min = 0, max = 30, message= "Experience should be 0 to 30 years")
    private int experience;

    @NotNull(message = "KeySkills is required")
    private List<String> keySkills;

    @NotBlank(message = "State field is mandatory")
    private String state;

    @NotBlank(message = "City field is mandatory")
    private String city;

    @NotNull(message = "Job category Id is mandatory")
    private Long jobCategoryId;

    @NotNull(message = "Job subcategory Id is mandatory")
    private Long jobSubCategoryId;

    @NotNull(message = "Employer Id id required")
    private long employerId;

    private LocalDate postedDate;
}
