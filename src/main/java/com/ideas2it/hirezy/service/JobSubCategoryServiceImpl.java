package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.JobCategoryMapper;
import com.ideas2it.hirezy.mapper.JobSubCategoryMapper;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.repository.JobSubCategoryRepository;
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
    private JobSubCategoryMapper jobSubCategoryMapper;

    @Autowired
    private  JobCategoryService jobCategoryService;

    @Autowired
    private JobCategoryMapper jobCategoryMapper;

    @Override
    public List<JobSubCategoryDto> getAllJobSubCategories() {
        List<JobSubCategory> jobSubcategories = StreamSupport.stream(jobSubCategoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return jobSubcategories.stream()
                .map(jobSubCategoryMapper::maptoJobSubCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobSubCategoryDto getJobSubCategoryById(Long id) {
        JobSubCategory jobSubcategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        return jobSubCategoryMapper.maptoJobSubCategoryDto(jobSubcategory);
    }

    @Override
    public JobSubCategoryDto createJobSubcategory(JobSubCategoryDto jobSubcategoryDto) {
        JobSubCategory jobSubCategory = jobSubCategoryMapper.toEntity(jobSubcategoryDto);
        jobSubCategory = jobSubCategoryRepository.save(jobSubCategory);
        return jobSubCategoryMapper.maptoJobSubCategoryDto(jobSubCategory);
    }

    @Override
    public JobSubCategoryDto updateJobSubcategory(Long id, JobSubCategoryDto jobSubCategoryDto) {
        JobSubCategory jobSubCategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        JobCategoryDto jobCategoryDto = jobCategoryService.getJobCategoryById(jobSubCategoryDto.getJobCategoryId());
        JobCategory jobCategory = jobCategoryMapper.mapToJobCategory(jobCategoryDto);
        jobSubCategory.setName(jobSubCategoryDto.getName());
        jobSubCategory.setJobCategory(jobCategory);
        jobSubCategory = jobSubCategoryRepository.save(jobSubCategory);
        return jobSubCategoryMapper.maptoJobSubCategoryDto(jobSubCategory);
    }

    @Override
    public void deleteJobSubcategory(Long id) {
        JobSubCategory jobSubcategory = jobSubCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSubcategory not found"));
        jobSubCategoryRepository.delete(jobSubcategory);
    }
}
