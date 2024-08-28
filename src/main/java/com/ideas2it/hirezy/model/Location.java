package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 *<p>This class is the model for accessing the location
 *</p>
 *@Author Audhithiyah
 */
@Entity
@Table(name = "locations")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL)
    private List<JobPost> jobPost;

}
