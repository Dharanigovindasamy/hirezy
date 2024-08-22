package com.ideas2it.hirezy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * This entity is used to categorize jobs under broad categories like IT jobs, Sales Jobs,etc.
 * Each JobCategory can have multiple JobSubcategories associated with it.
 * Example :
 *  A jobCategory with the nae "IT" jobs might have subcategories like "Software developer","System Analyst",etc.
 *  Attributes:
 *  - id: Primary key, auto-generated.
 *  - name: Name of the job category (e.g., "IT Jobs").
 *  - description: A brief description of the job category.
 *  - subcategories: A set of JobSubcategory entities that belong to this category.
 * @Author kishore
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JobCategory")
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JobSubCategory> subcategories;

}
