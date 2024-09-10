package com.ideas2it.hirezy.dto;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hirezy.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *  This class used for adding employee profile details into the database table by giving the data from user as Http json format
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String name;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must be a string")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid qualification")
    private String qualification;

    @NotNull
    private int yearOfExperience;

    @NotBlank(message = "Company type cannot be blank")
    @Size(max = 30, message = "Company type cannot exceed 50 characters")
    private String currentCompany;

    @NotBlank(message = "Designation cannot be blank")
    @Size(max = 20, message = "Designation type cannot exceed 20 characters")
    private String designation;

    @NotNull
    private int noticePeriod;

    @NotNull(message = "User ID cannot be null")
    private long userId;

    private Gender gender;

    @NotNull(message = "Skills must be in a list separated by commas")
    private List<String> keySkills;
}
