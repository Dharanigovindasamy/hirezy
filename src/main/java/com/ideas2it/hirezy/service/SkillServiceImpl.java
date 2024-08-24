package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.SkillDto;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.SkillMapper;
import com.ideas2it.hirezy.model.Skill;
import com.ideas2it.hirezy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDto saveSkill(SkillDto skillDto) {
        String skillName = skillDto.getSkillName();
        for (Skill skill : skillRepository.findAll())
            if (skillName.equals(skill.getSkillName())) {
                throw new ResourceAlreadyExistsException("Skill Name - " + skillName + "AlreadyExist");
            }
        Skill skill = SkillMapper.mapSkillDto(skillDto);
        return(SkillMapper.mapSkill(skillRepository.save(skill)));
    }

    @Override
    public SkillDto updateSkill(SkillDto skillDto) {
        long skillId = skillDto.getSkillId();
        for (Skill skill : skillRepository.findAll())
            if (skillId == skill.getSkillId()) {
                skill = SkillMapper.mapSkillDto(skillDto);
                return(SkillMapper.mapSkill(skillRepository.save(skill)));
            }
        throw new ResourceNotFoundException("Skill Id - " + skillId + "AlreadyExist");
    }

    @Override
    public SkillDto retrieveSkillById(long skillId) {
        Skill skill = skillRepository.findBySkillIdAndIsActiveTrue(skillId);
        if(null == skill) {
            throw new ResourceNotFoundException("Skill Id"+ skillId + " not found");
        }
        return SkillMapper.mapSkill(skill);
    }

    @Override
    public List<SkillDto> retrieveAllSkills() {
        List<SkillDto> skillDtos = new ArrayList<>();
        for (Skill skill : skillRepository.findByIsActiveTrue()) {
            if(skill == null) {
                throw new ResourceNotFoundException("There is no skills please add the Skills");
            }
            skillDtos.add(SkillMapper.mapSkill(skill));
        }
        return skillDtos;
    }

    @Override
    public boolean deleteSkill(long skillId) {
        Skill skill = skillRepository.findBySkillIdAndIsActiveTrue(skillId);
        if(null == skill) {
            throw new ResourceNotFoundException("Skill Id"+ skillId + " not found");
        }
        skill.setActive(false);
        skillRepository.save(skill);
        return true;
    }

}
