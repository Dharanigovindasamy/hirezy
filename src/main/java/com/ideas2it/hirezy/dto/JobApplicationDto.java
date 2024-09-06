package com.ideas2it.hirezy.dto;

import java.time.LocalDateTime;

import com.ideas2it.hirezy.model.enums.JobApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private JobApplicationStatus status;
    @NotNull
    private LocalDateTime appliedDate;
    @NotNull
    private long employeeId;
    @NotNull
    private long jobPostId;
    private EmployeeDto employeeDto;
    private JobPostDto jobPostDto;
}
