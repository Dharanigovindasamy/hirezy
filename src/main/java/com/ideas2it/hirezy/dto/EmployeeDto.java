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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author dharani.govindhasamy
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long id;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    //@NotNull
    private LocalDate dateOfBirth;
  //  private int age;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String resume;

    private String photo;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid city")
    private String city;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid qualification")
    private String qualification;
    @NotNull
    private float percentage;
    @NotNull
    private int yearOfPassOut;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter work mode")
    private String workMode;
    @NotNull
    //@Size(min = 1, max = 30,
     //       message= "Experience should be 0 to 30 years")
    private int yearOfExperience;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter current company")
    private String currentCompany;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter designation")
    private String designation;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter company city")
    private String companyCity;
    @NotNull
    private int noticePeriod;
}
