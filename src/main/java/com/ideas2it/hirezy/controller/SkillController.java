package com.ideas2it.hirezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.dto.SkillDto;
import com.ideas2it.hirezy.service.SkillService;

/**
 * This class is the controller class for skill.
 * It wil manage all the request from the user.
 * @author paari
 */
@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    /**
     * This Method is to add Skill to the Database.
     * @param skillDto {@link SkillDto}
     *     It contains the Skill name and the ID will be Auto Generated.
     * @return SkillDto
     *     It contains the Skill details which is added to the database.
     */
    @PostMapping
    public ResponseEntity<SkillDto> createSkill
    (@RequestBody SkillDto skillDto) {
        return new ResponseEntity<>
                (skillService.saveSkill(skillDto),
                        HttpStatus.CREATED);
    }

    /**
     * This method is to Retrieve all the skills from the database.
     * @return List<SkillDto>
     *     It contains the list of skill in the database.
     */
    @GetMapping
    public ResponseEntity<List<SkillDto>> getSkills() {
        return new ResponseEntity<>(skillService.retrieveAllSkills(),HttpStatus.OK);
    }

    /**
     * This Method is to retrieve the single skill.
     * @param skillId
     *     It is the Id of the skill to be retrieved
     * @return SkillDto
     *     It contains the Single skill.
     */
    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable long skillId) {

        return new ResponseEntity<>(skillService.retrieveSkillById(skillId),HttpStatus.OK);
    }

    /**
     * This Method is to update Skill in the Database.
     * @param skillDto {@link -SkillDto}
     *     It contains the Skill name and the ID of the Skill.
     * @return SkillDto
     *     It contains the Skill details which is updated in the database.
     */
    @PutMapping
    public ResponseEntity<SkillDto> updateSkill(@RequestBody SkillDto skillDto) {
        return new ResponseEntity<>(skillService.updateSkill (skillDto),HttpStatus.OK);
    }

    /**
     * This method is to Delete skill from the Database.
     * @param skillId
     *     It is id of the employee to be deleted.
     * @return String
     *     It will return success if deleted else return error.
     */
    @DeleteMapping("/{skillId}")
    public ResponseEntity<String> deleteSkillById(@PathVariable long skillId){
        if (skillService.deleteSkill(skillId)) {
            return new ResponseEntity<>("Skill Id - " + skillId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error in deleting the Skill Id - " + skillId , HttpStatus.NO_CONTENT);
    }
}
