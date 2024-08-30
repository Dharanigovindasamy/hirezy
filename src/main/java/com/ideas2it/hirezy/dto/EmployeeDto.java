package com.ideas2it.hirezy.dto;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hirezy.model.enums.GenderEnum;
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
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String resume;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid city")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid qualification")
    private String qualification;

    @NotNull
    @Size(min = 1, max = 30, message= "Experience should be 0 to 30 years")
    private int yearOfExperience;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter current company")
    private String currentCompany;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter designation")
    private String designation;

    @NotNull
    private int noticePeriod;

    @NotNull(message = "User ID cannot be null")
    private long userId;

    @NotBlank(message = "Gender has to set")
    private GenderEnum gender;


    @NotNull(message = "enter skills")
    private List<String> keySkills;
}
