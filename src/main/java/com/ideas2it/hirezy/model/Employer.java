package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@Table(name = "employer_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "industry_type")
    private String industryType;

    private boolean isDeleted = false;

}
