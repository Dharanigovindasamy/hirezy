package com.ideas2it.hirezy.dto;

import com.ideas2it.hirezy.model.enums.Gender;
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
 * This class represents a Data Transfer Object (DTO) for the Employer entity.
 * It is used to transfer employer-related data between different layers of the application.
 * The EmployerDto class includes fields like ID, name, company name, description,
 * industry type, and company type, with validation constraints to ensure data integrity.
 * </p>
 * @author kishore
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployerDto {

    @NotNull(message = "Employer ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 20, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @NotBlank(message = "Industry type cannot be blank")
    @Size(max = 50, message = "Industry type cannot exceed 50 characters")
    private String industryType;

    @NotBlank(message = "Company type cannot be blank")
    @Size(max = 30, message = "Company type cannot exceed 50 characters")
    private String companyType;

    @NotNull(message = "User ID cannot be null")
    private long userId;

    @NotBlank(message = "Gender field is mandatory")
    private Gender gender;
}