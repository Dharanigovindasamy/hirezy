package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 *<p>This class is the model for accessing the location
 *</p>
@Author Audhithiyah
* @Version v1
 */
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String state;

    @Column(nullable = false,unique = true)
    private String city;

//    @ManyToOne
//    @JoinColumn(name = "job_post_Id")
//    private JobPost jobPost;


//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<JobPosting> jobPostings = new HashSet<>();
}
