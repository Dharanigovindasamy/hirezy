package com.ideas2it.hirezy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostDto {
    private Long id;
    private String title;
    private LocalDate postedDate;
    private String jobDescription;
    private String experience;
    private LocationDto location;
    private JobCategoryDto jobCategory;
    private EmployerDto employer;
}
