package com.ideas2it.hirezy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This  is the skill class which will define the both Employer needed skills.
 * And also Employee Skills.
 * It contains skillId and skill name.
 * @author paari
 */
@Entity
@Table(name = "skill")
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

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
