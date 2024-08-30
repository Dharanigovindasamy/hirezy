package com.ideas2it.hirezy.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Enter job post title")
    private String title;

    @NotBlank(message = "Enter job description")
    private String jobDescription;

    @NotNull(message = "enter experience")
    private int experience;

    @NotNull(message = "enter skills")
    private List<String> keySkills;

    @NotBlank(message = "Enter state")
    private String state;

    @NotBlank(message = "Enter city")
    private String city;

    @NotNull(message = "Enter job category Id")
    private Long jobCategoryId;

    @NotNull(message = "Enter job subcategory Id")
    private Long jobSubCategoryId;

    @NotNull(message = "Enter job employer Id")
    private long employerId;

    private LocalDate postedDate;
}
