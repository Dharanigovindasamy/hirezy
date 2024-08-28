package com.ideas2it.hirezy.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     The `JobPost` entity represents a job posting in the system. It includes
 *     various attributes related to a job, such as title, description, key skills,
 *     location, job category, employer, and the date the job was posted.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_posting")
@Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "job_title")
    private String title;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "experience")
    private int experience;

    @ElementCollection
    @CollectionTable(name = "job_key_skill", joinColumns = @JoinColumn(name = "job_post_id"))
    @Column(name = "key_skill")
    private List<String> keySkills;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(name = "posted_date")
    private LocalDate postedDate;
}
