package com.ideas2it.hirezy.model;

import java.time.LocalDate;
import java.util.List;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
 * @author kishorekumar.n
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_subcategory_id")
    private JobSubCategory jobSubCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(name = "posted_date")
    private LocalDate postedDate;
}
