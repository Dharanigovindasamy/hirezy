package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(unique = true)
    private String website;

//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<JobPosting> jobPostings = new HashSet<>();
}
