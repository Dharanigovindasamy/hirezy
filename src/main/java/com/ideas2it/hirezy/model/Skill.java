package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This  is the skill class which will define the both Employer needed skills.
 * And also Employee Skills.
 * It contains skillId and skill name.
 * @author paari
 */
@Entity
@Table(name = "skills")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long skillId;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "is_active")
    private boolean isActive = true;
}
