package com.ideas2it.hirezy.model;

import java.util.Set;

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

/**
 * This entity is used to categorize jobs under broad categories like IT jobs, Sales Jobs,etc.
 * Each JobCategory can have multiple JobSubcategories associated with it.
 * Example :
 * A jobCategory with the nae "IT" jobs might have subcategories like "Software developer","System Analyst",etc.
 * Attributes:
 *
 * @author kishorekumar.n
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
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobSubCategory> subCategories;

}
