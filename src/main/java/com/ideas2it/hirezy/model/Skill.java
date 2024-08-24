package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;

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
