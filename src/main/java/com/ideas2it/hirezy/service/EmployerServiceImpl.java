package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.repository.EmployerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ideas2it.hirezy.mapper.EmployerMapper.convertDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployerMapper.convertEntityToDto;

/**
 * This class handles
 * the business logic related to employer operations, including creating,
 * updating, retrieving, and deleting employer records. Additionally,
 * it manages job posts associated with employers.
 * @Author kishore
 */
@Service
 public class EmployerServiceImpl implements EmployerService {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    @Lazy
    private JobPostService jobPostService;

    public EmployerDto createEmployer(EmployerDto employerDto) {
        Employer employer = convertDtoToEntity(employerDto);
        logger.info("Creating a new employer with name: {}", employer.getName());
        logger.info("Company has been successfully created");
        Employer savedEmployer = employerRepository.save(employer);
        logger.info("Employer created successfully with ID: {}", savedEmployer.getId());
        return convertEntityToDto(employerRepository.save(employer));
    }
    public List<EmployerDto> getAllEmployer() {
        logger.info("Fetching all employers");
        List<EmployerDto> result = new ArrayList<>();
        List<Employer> employers = employerRepository.findByIsDeletedFalse();
        if(employers.isEmpty()) {
            logger.warn("No employers found");
        } else {
            for (Employer employer : employers) {
                result.add(convertEntityToDto(employer));
            }
            logger.info("Total employers found: {}", employers.size());
        }
        return result;
    }

    public void removeEmployer(int id) {
        logger.info("Removing employer with ID: {}", id);
        Employer employer = employerRepository.findByIsDeletedFalseAndId(id);
        if (employer == null) {
            logger.error("Employer not found with ID: {}", id);
            throw new ResourceNotFoundException("Employer not found with ID: " + id);
        }
        employer.setDeleted(true);
        employerRepository.save(employer);
        logger.info("Employer with ID: {} has been marked as deleted", id);

    }

    public EmployerDto updateEmployer(int id, EmployerDto employerDto)  {
        logger.info("Updating employer with ID: {}", id);
        Employer convertEmployer = convertDtoToEntity(employerDto);
        Employer existingEmployer =  employerRepository.findByIsDeletedFalseAndId(id);
        if (existingEmployer == null) {
            logger.error("Employer not found with ID : {}", id);
            throw new ResourceNotFoundException("Employer not found with ID: " + id);
        }
        existingEmployer.setName(convertEmployer.getName());
        logger.info("Employer with ID: {} has been updated", id);
        return convertEntityToDto(employerRepository.save(existingEmployer));
    }

    public EmployerDto getEmployerById(long id) {
        logger.info("Fetching employer with ID: {}", id);
        Employer employer = employerRepository.findByIsDeletedFalseAndId((int) id);
        if (employer == null) {
            logger.error(" Employer not found with ID : {}", id);
            throw new ResourceNotFoundException("Employer not found with ID: " + id);
        }
        return convertEntityToDto(employer);
    }

    public JobPostDto updateJobPost(long employerId, long jobId, JobPostDto jobPostDto) {
        logger.info("Updating job post with id: {} for employer with id: {}", jobId, employerId);
        return jobPostService.updateJobPost(jobId, jobPostDto);
    }

    public void deleteJobPost(Long jobId) {
        logger.info("Deleting job post with ID: {}", jobId);
        jobPostService.deleteJobPost(jobId);
    }

    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        logger.info("Fetching all job posts for employer with ID: {}", employerId);
        return jobPostService.getAllJobPostsByEmployer(employerId);
    }
}
