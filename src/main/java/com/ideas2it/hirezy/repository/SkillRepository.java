package com.ideas2it.hirezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.Skill;

/**
 * This class is the repository class for Skill.
 * This is used by both Employee and Employer.
 * @author paari
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /**
     * This Method will return the List of skill if it's delete is false.
     * @return List<Skill>
     *     It contains all the skill details.
     */
    List<Skill> findByIsDeletedFalse();

    /**
     * This Method will return single skill based on the skillId.
     * @param skillId
     *     The id of the Skill to be retrieved
     * @return Skill
     *     It Contains the Single Skill Details.
     */
    Skill findBySkillIdAndIsDeletedFalse(long skillId);

}
