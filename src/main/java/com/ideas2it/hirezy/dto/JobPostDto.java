package com.ideas2it.hirezy.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Job descripiton cannot be blank")
    private String jobDescription;

    @Pattern(regexp =  "^[0-9]*$",message ="Experience should be in digit" )
    private int experience;

    private List<String> keySkills;

    @NotBlank(message = "Name is Required")
    @Size(min=2, max=30, message = "State should not exceed 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "State should be alphabets" )
    private String state;

    @NotBlank(message = "City is Required")
    @Size(min=2, max=30, message = "City should not exceed 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Name should be alphabets" )
    private String city;

    @NotNull(message = "JobCategory ID is Required")
    private Long jobCategoryId;

    @NotNull(message = "Employer ID is Required")
    private Long employerId;

    private LocalDate postedDate;
}
