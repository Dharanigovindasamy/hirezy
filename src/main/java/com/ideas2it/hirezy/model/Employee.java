package com.ideas2it.hirezy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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
@Table(name = "employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "resume", nullable = false)
    private String resume;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "qualification", nullable = false)
    private String qualification;

    @Column(name = "year_of_experience", nullable = false)
    private int yearOfExperience;

    @Column(name = "current_company", nullable = false)
    private String currentCompany;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "notice_period", nullable = false)
    private int noticePeriod;

    @Column
    private boolean isDeleted;

    @ElementCollection
    @CollectionTable(name = "employee_key_skill", joinColumns = @JoinColumn(name = "job_post_id"))
    @Column(name = "key_skill")
    private List<String> keySkills;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id",  referencedColumnName = "id" )
    private User user;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<JobApplication> jobApplications;

}
