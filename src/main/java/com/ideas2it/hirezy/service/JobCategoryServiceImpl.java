package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.JobCategoryMapper;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.repository.JobCategoryRepository;

/**
 * <p>
 *     This class used for job categories functionalities like CRUD Operations
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public class JobCategoryServiceImpl  implements JobCategoryService {
    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    private static final Logger logger = LogManager.getLogger(JobCategoryServiceImpl.class);

    @Override
    public List<JobCategoryDto> getAllJobCategories() {
        List<JobCategory> jobCategories = new ArrayList<>();
        Iterable<JobCategory> allJobCategories = jobCategoryRepository.findAll();
        for (JobCategory jobCategory : allJobCategories) {
            if (!jobCategory.isDeleted()) {
                jobCategories.add(jobCategory);
            }
        }
        List<JobCategoryDto> jobCategoryDtos = new ArrayList<>();
        for (JobCategory jobCategory : jobCategories) {
            jobCategoryDtos.add(JobCategoryMapper.mapTojobCategoryDto(jobCategory));
        }
        logger.info("Retrieving list of all jobCategory's");
        return jobCategoryDtos;
    }

    @Override
    public JobCategoryDto getJobCategoryById(Long id) {
        JobCategory jobCategory = jobCategoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobCategory not found with ID: " + id));
        return JobCategoryMapper.mapTojobCategoryDto(jobCategory);
    }

    @Override
    public JobCategoryDto createJobCategory(JobCategoryDto jobCategoryDto) {
        if (jobCategoryRepository.existsByName(jobCategoryDto.getName())) {
            logger.error("jobCategory already exists with name: {}", jobCategoryDto.getName());
            throw new ResourceAlreadyExistsException("jobCategory already exists with name: " + jobCategoryDto.getName());
        }
        JobCategory jobCategory = JobCategoryMapper.mapToJobCategory(jobCategoryDto);
        JobCategory createdJobCategory = jobCategoryRepository.save(jobCategory);
        logger.info("Adding jobCategoryDto with name: {}",jobCategoryDto.getName());
        return JobCategoryMapper.mapTojobCategoryDto(createdJobCategory);
    }

    @Override
    public JobCategoryDto updateJobCategory(Long id, JobCategoryDto jobCategoryDto) {
        JobCategory existingJobCategory = jobCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobCategory not found with ID: " + id));
        existingJobCategory.setName(jobCategoryDto.getName());
        JobCategory updatedJobCategory = jobCategoryRepository.save(existingJobCategory);
        logger.info("Updated jobCategory with name {}",jobCategoryDto.getName());
        return JobCategoryMapper.mapTojobCategoryDto(updatedJobCategory);
    }

    @Override
    public void deleteJobCategory(Long id) {
        JobCategory existingJobCategory = jobCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobCategory not found with ID: " + id));
        existingJobCategory.setDeleted(true);
        jobCategoryRepository.save(existingJobCategory);
        logger.info("Department is Deleted {}",existingJobCategory.getName());
    }
}
