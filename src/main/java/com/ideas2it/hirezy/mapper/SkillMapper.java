package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.SkillDto;
import com.ideas2it.hirezy.model.Skill;

/**
 * This class is the mapper class which will map object to Dto and vice-versa.
 * @author paari
 */
public class SkillMapper {

    /**
     * This Method is to map SkillDto to skill object.
     * @param skillDto {@link SkillDto}
     * @return Skill
     *     It contains the Skill details
     */
    public static Skill mapSkillDto(SkillDto skillDto) {
        return Skill.builder()
                .skillId(skillDto.getSkillId())
                .skillName(skillDto.getSkillName())
                .build();
    }

    /**
     * This method is to map Skill to SkillDto.
     * @param skill {@link Skill}
     * @return SkillDto
     *     It contains the skillDto details in the database.
     */
    public static SkillDto mapSkill(Skill skill) {
        return SkillDto.builder()
                .skillId(skill.getSkillId())
                .skillName(skill.getSkillName())
                .build();
    }
}
