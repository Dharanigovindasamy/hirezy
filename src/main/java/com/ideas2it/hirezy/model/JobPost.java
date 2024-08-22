package com.ideas2it.hirezy.model;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_postings")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "experience")
    private String experience;

  //  @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "employer_id", nullable = false)
  //  private Employer employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobcategory_id", nullable = false)
    private JobCategory jobCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobsubcategory_id", nullable = false)
    private JobSubCategory jobSubcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
