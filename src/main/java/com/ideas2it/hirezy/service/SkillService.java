package com.ideas2it.hirezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.SkillDto;



/**
 * This is th service class which will manage all the CRUD operations.
 * This also manage the business logic for the application.
 * @author paari
 */
@Service
public interface SkillService {

    /**
     * This method is to save Skill to the database.
     * @param skillDto {@link SkillDto}
     *     It contains only the name and the id will be auto generated.
     * @return SkillDto
     *     It contains the save skill with generated id.
     */
    SkillDto saveSkill(SkillDto skillDto );

    /**
     * This method is to Update Skill to the database.
     * @param skillDto {@link SkillDto}
     *     It contains updated name of the skill.
     * @return SkillDto
     *     It contains the updated skill with id and the name.
     */
    SkillDto updateSkill(SkillDto skillDto);

    /**
     * This method is to retrieve a single skill by id.
     * @return SkillDto
     *     It contains the Skill id and Name.
     */
    SkillDto retrieveSkillById(long skillId);

    /**
     * This methos will return all the skills in the database as List.
     * @return List<SkillDto>
     *      It contains list of SkillDto
     */
    List<SkillDto> retrieveAllSkills();

    /**
     * This Method is to delete skill form the database.
     * @param skillId
     *     This is the id of the Skill to be deleted.
     * @return boolean
     *     true if deleted else false.
     */
    boolean deleteSkill(long skillId);


}
