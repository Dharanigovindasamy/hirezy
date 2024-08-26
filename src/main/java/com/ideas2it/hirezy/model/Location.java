package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 *<p>This class is the model for accessing the location
 *</p>
 *@Author Audhithiyah
 */
@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String state;

    @Column(nullable = false,unique = true)
    private String city;

    @Column(nullable = false)
    private boolean isActive = false;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private List<JobPost> jobPost;

}
