package com.ideas2it.hirezy.dto;

import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobPost;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime AppliedDate;
    private Long employeeId;
    private Long jobPostId;
}
