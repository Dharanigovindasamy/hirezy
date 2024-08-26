package com.ideas2it.hirezy.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostDto {
    private Long id;
    private String title;
    private String jobDescription;
    private List<String> keySkills;
    private String state;
    private String city;
    private Long jobCategoryId;
    private Long employerId;
    private LocalDate postedDate;
}
