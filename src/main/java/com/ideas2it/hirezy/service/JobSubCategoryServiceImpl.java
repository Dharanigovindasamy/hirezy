package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.JobCategoryMapper;
import com.ideas2it.hirezy.mapper.JobSubCategoryMapper;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.repository.JobSubCategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JobSubCategoryServiceImpl implements JobSubCategoryService {

    @Autowired
    private JobSubCategoryRepository jobSubCategoryRepository;

    @Autowired
    private  JobCategoryService jobCategoryService;

    private static final Logger logger = LogManager.getLogger(JobSubCategoryServiceImpl.class);

    @Override
    public List<JobSubCategoryDto> getAllJobSubCategories() {
        List<JobSubCategory> jobSubcategories = StreamSupport.stream(jobSubCategoryRepository.findAll().spliterator(), false)
                .toList();

        return jobSubcategories.stream()
                .map(JobSubCategoryMapper::maptoJobSubCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobSubCategoryDto getJobSubCategoryById(Long id) {
        JobSubCategory jobSubcategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        return JobSubCategoryMapper.maptoJobSubCategoryDto(jobSubcategory);
    }

    @Override
    public JobSubCategoryDto createJobSubcategory(JobSubCategoryDto jobSubcategoryDto) {
        logger.debug("Request to create JobSubCategory with details: {}", jobSubcategoryDto);
        JobCategoryDto jobCategory = jobCategoryService.getJobCategoryById(jobSubcategoryDto.getJobCategoryId());
        if (jobCategory == null) {
            throw new ResourceNotFoundException("JobCategory not found");
        }

        JobSubCategory jobSubCategory = JobSubCategoryMapper.maptoJobSubCategory(jobSubcategoryDto);
        jobSubCategory.setJobCategory(JobCategoryMapper.mapToJobCategory(jobCategory));
        return JobSubCategoryMapper.maptoJobSubCategoryDto(jobSubCategoryRepository.save(jobSubCategory));
    }

    @Override
    public JobSubCategoryDto updateJobSubcategory(Long id, JobSubCategoryDto jobSubCategoryDto) {
        JobSubCategory jobSubCategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobSubCategoryDto.getJobCategoryId());
        JobCategory jobCategory = JobCategoryMapper.mapToJobCategory(jobCategoryDto);
        jobSubCategory.setName(jobSubCategoryDto.getName());
        jobSubCategory.setJobCategory(jobCategory);
        jobSubCategory = jobSubCategoryRepository.save(jobSubCategory);
        return JobSubCategoryMapper.maptoJobSubCategoryDto(jobSubCategory);
    }

    @Override
    public void deleteJobSubcategory(Long id) {
        JobSubCategory jobSubcategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        jobSubCategoryRepository.delete(jobSubcategory);
    }
}
