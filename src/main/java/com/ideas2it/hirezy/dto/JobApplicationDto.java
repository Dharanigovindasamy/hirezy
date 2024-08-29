package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 *     This class used for job application information given by employer to employee
 *  It can edited by employer and seen by user
 *  This class added with validation annotations
 *
 * @author dharani.govindhasamy
 * @version 1
 * </p>
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {
    private long id;

    @NotBlank
    private String status;

    @NotNull
    private LocalDateTime appliedDate;
    private Long employeeId;
    private Long jobPostId;
    private EmployeeDto employee;
    private JobPostDto jobPostDto;
}
