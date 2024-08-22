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

import java.util.Set;

@Entity
@Table(name = "JobCategory")
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JobSubcategory> subcategories;


}
