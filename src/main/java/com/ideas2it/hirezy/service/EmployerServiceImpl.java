package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.repository.EmployerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ideas2it.hirezy.mapper.EmployerMapper.convertDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployerMapper.convertEntityToDto;

@Service
 public class EmployerServiceImpl implements EmployerService {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobPostService jobPostService;

    public EmployerDto createEmployer(EmployerDto employerDto) {
        Employer employer = convertDtoToEntity(employerDto);
//        if(companyRepository.existsByCompanyName(company.getCompanyName())) {
//            logger.warn("trying to add a duplicate entry for an existing company");
//        }
        logger.info("Company has been successfully created");
        return convertEntityToDto(employerRepository.save(employer));
    }
    public List<EmployerDto> getAllEmployer() {
        List<EmployerDto> result = new ArrayList<>();
        List<Employer> companies = employerRepository.findByIsDeletedFalse();
        if(companies.isEmpty()) {
            logger.warn("companies are not present ");
        } else {
            for (Employer employer : companies) {
                result.add(convertEntityToDto(employer));
            }
        }
        return result;
    }

    public void removeEmployer(int id) {
        Employer employer = employerRepository.findByIsDeletedFalseAndId(id);
        employer.setDeleted(true);
        employerRepository.save(employer);
        logger.info("company has been removed of id ..{}",id);

    }

    public EmployerDto updateEmployer(int id, EmployerDto employerDto)  {
        Employer convertEmployer = convertDtoToEntity(employerDto);
        Employer existingEmployer =  employerRepository.findByIsDeletedFalseAndId(id);
        existingEmployer.setCompanyName(convertEmployer.getCompanyName());
        logger.info("company details has been updated of id..{}",id);
        return convertEntityToDto(employerRepository.save(existingEmployer));
    }

    public EmployerDto getEmployerById(int id) {
        Employer employer = employerRepository.findByIsDeletedFalseAndId(id);
//        if(null == company) {
//            throw new EmployeeException("Department Not found with Id : " + 1);
//        }
        return convertEntityToDto(employer);
    }

    public JobPostDto createJobPost(long employerId, JobPostDto jobPostDto) {
        // Fetch the existing employer
        Employer employer = employerRepository.findByIsDeletedFalseAndId((int) employerId);

        // Check if the employer exists
        if (employer == null) {
            throw new ResourceNotFoundException("Employer not found with id " + employerId);
        }

        // Set the employer in the JobPostDto
        jobPostDto.setEmployer(convertEntityToDto(employer));

        // Save the job post
        return jobPostService.createJobPost(jobPostDto);
    }

    public JobPostDto updateJobPost(Long jobId, JobPostDto jobPostDto) {
        return jobPostService.updateJobPost(jobId, jobPostDto);
    }

    public void deleteJobPost(Long jobId) {
        jobPostService.deleteJobPost(jobId);
    }

    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        return jobPostService.getAllJobPostsByEmployer(employerId);
    }
}
