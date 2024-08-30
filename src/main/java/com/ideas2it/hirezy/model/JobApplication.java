package com.ideas2it.hirezy.model;

import com.ideas2it.hirezy.model.enums.JobApplicationStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>
 *    This class used for after filling the job applications by employee,
 *    can access the applications by employer by editing the status and viewed by employee
 *    along with applied date and status
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 */
@Entity
@Table(name = "job_application")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status;

    @Column(name = "applied_date")
    private LocalDateTime appliedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobPost jobPost;
}
