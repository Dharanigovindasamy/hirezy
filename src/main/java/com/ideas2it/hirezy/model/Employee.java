package com.ideas2it.hirezy.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * <p>
 *     This class used for entering employee profile details into the entity table.
 *  * Contains setter and getter method to add data into it
 * </p>
 *
 * @author Dharani Govindhasamy
 * @version 1
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long employeeId;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "resume", nullable = false)
    private String resume;

    @Column(name = "photo",  nullable = false)
    private String photo;

    @Column(name = "city",  nullable = false)
    private String city;

    @Column(name = "qualification", nullable = false)
    private String qualification;

    @Column(name = "percentage", nullable = false)
    private float percentage;

    @Column(name = "year_of_pass_out", nullable = false)
    private int yearOfPassOut;

    @Column(name = "work_mode", nullable = false)
    private String workMode;

    @Column(name = "year_of_experience", nullable = false)
    private int yearOfExperience;

    @Column(name = "current_company", nullable = false)
    private String currentCompany;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "company_city", nullable = false)
    private String companyCity;

    @Column(name = "notice_period", nullable = false)
    private int noticePeriod;

    @Column(name = "is_removed")
    private boolean isRemoved;

}
