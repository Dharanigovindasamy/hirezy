package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.EmployerRepository;
import static com.ideas2it.hirezy.mapper.EmployerMapper.convertDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployerMapper.convertEntityToDto;

/**
 * <p>
 *     This class handles
 * the business logic related to employer operations, including creating,
 * updating, retrieving, and deleting employer records. Additionally,
 * it manages job posts associated with employers.
 * </p>
 * @author kishore
 */
@Service
 public class EmployerServiceImpl implements EmployerService {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    @Lazy
    private JobPostService jobPostService;

    @Autowired
    private UserService userService;

    @Override
    public EmployerDto createEmployer(EmployerDto employerDto) {
        User user = userService.retrieveUserById(employerDto.getUserId());
        Employer employer = convertDtoToEntity(employerDto,user);
        logger.info("Creating a new employer with name: {}", employer.getName());
        logger.info("Company has been successfully created");
        Employer savedEmployer = employerRepository.save(employer);
        logger.info("Employer created successfully with ID: {}", savedEmployer.getId());
        return convertEntityToDto(employerRepository.save(employer));
    }

    @Override
    public List<EmployerDto> getAllEmployers() {
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

    @Override
    public void removeEmployer(long employerId) {
        logger.info("Removing employer with ID: {}", employerId);
        Employer employer = employerRepository.findByIsDeletedFalseAndId(employerId);
        if (employer == null) {
            logger.error("Employer not found with ID: {}", employerId);
            throw new ResourceNotFoundException("Employer not found with ID: " + employerId);
        }
        employer.setDeleted(true);
        employerRepository.save(employer);
        logger.info("Employer with ID: {} has been marked as deleted", employerId);

    }

    @Override
    public EmployerDto updateEmployer(EmployerDto employerDto)  {
        Employer existingEmployer =  employerRepository.findByIsDeletedFalseAndId(employerDto.getId());
        if (existingEmployer == null) {
            logger.error("Employer not found with ID : {}",employerDto.getId());
            throw new ResourceNotFoundException("Employer not found with ID: " + employerDto.getId());
        }
        Employer employer = convertDtoToEntity(employerDto);
        logger.info("Employer with ID: {} has been updated", employerDto.getId());
        return convertEntityToDto(employerRepository.save(employer));
    }

    @Override
    public EmployerDto getEmployerById(long employerId) {
        logger.info("Fetching employer with ID: {}", employerId);
        Employer employer = employerRepository.findByIsDeletedFalseAndId(employerId);
        if (employer == null) {
            logger.error(" Employer not found with ID : {}", employerId);
            throw new ResourceNotFoundException("Employer not found with ID: " + employerId);
        }
        return convertEntityToDto(employer);
    }

    @Override
    public JobPostDto updateJobPost(long employerId, long jobId, JobPostDto jobPostDto) {
        logger.info("Updating job post with id: {} for employer with id: {}", jobId, employerId);
        return jobPostService.updateJobPost(jobId, jobPostDto);
    }

    @Override
    public void deleteJobPost(Long jobId) {
        logger.info("Deleting job post with ID: {}", jobId);
        jobPostService.deleteJobPost(jobId);
    }

    @Override
    public List<JobPostDto> getAllJobPostsByEmployer(Long employerId) {
        logger.info("Fetching all job posts for employer with ID: {}", employerId);
        return jobPostService.getAllJobPostsByEmployer(employerId);
    }
}
