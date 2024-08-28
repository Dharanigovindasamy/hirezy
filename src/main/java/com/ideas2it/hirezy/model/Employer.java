package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * <p>
 *     This class used for entity of employer details contains company details
 * that has to be used for job post
 *
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 */
@Builder
@Getter
@Entity
@Table(name = "employer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "industry_type")
    private String industryType;

    private boolean isDeleted;

    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER)
    private List<JobPost> jobPost;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id",  referencedColumnName = "id" )
    private User user;

}
