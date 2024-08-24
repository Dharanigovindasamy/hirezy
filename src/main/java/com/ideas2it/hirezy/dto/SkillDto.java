package com.ideas2it.hirezy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class is the SkillDto class which contains
 * Skill id and Skill Name.
 * @author paari
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private long skillId;
    private String skillName;
}
