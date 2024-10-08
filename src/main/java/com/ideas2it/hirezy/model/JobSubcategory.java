package com.ideas2it.hirezy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Job Subcategory.
 *
 * This entity is used to further classify jobs under a specific job category.
 * Each JobSubcategory belongs to a JobCategory and provides a more specific classification.
 *
 * Example:
 * - A JobSubcategory with the name "Software Developer" might belong to the JobCategory "IT Jobs".
 *
 * Attributes:
 * - id: Primary key, auto-generated.
 * - name: Name of the job subcategory (e.g., "Software Developer").
 * - description: A brief description of the job subcategory.
 * - jobCategory: The JobCategory to which this subcategory belongs.
 * @Author kishore
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JobSubcategory")
public class JobSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_category_id", nullable = false)
    private JobCategory jobCategory;
}
