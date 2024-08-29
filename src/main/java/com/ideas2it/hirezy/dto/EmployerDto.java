package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * <p>
 * This class represents a Data Transfer Object (DTO) for the Employer entity.
 * It is used to transfer employer-related data between different layers of the application.
 * </p>
 * <p>
 * The EmployerDto class includes fields like ID, name, company name, description,
 * industry type, and company type, with validation constraints to ensure data integrity.
 * </p>
 * @Author kishore
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployerDto {

    @NotNull(message = "Employer ID cannot be null")
    private Long Id;

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
    @Size(max = 50, message = "Company type cannot exceed 50 characters")
    private String companyType;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}