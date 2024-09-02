package com.ideas2it.hirezy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ideas2it.hirezy.mapper.JobApplicationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.*;
import com.ideas2it.hirezy.model.enums.GenderEnum;
import com.ideas2it.hirezy.model.enums.JobApplicationStatus;
import com.ideas2it.hirezy.repository.JobApplicationRepository;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private JobPostService jobPostService;

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;

    JobApplication jobApplication;
    JobApplicationDto jobApplicationDto;

    @BeforeEach
    void setup() {
        jobApplication = JobApplication.builder()
                .id(1L)
                .status(JobApplicationStatus.APPLIED)
                .appliedDate(LocalDate.parse("2024-08-08").atStartOfDay())
                .employee(Employee.builder()
                        .id(1L)
                        .name("John")
                        .dateOfBirth(LocalDate.of(2000, 10, 10))
                        .resume("resume")
                        .city("chennai")
                        .qualification("BE")
                        .yearOfExperience(3)
                        .currentCompany("ideas2it")
                        .designation("software developer")
                        .noticePeriod(3)
                        .keySkills(List.of("html", "css", "java"))
                        .user(User.builder()
                                .id(1L)
                                .emailId("john@gmail.com")
                                .password("12343")
                                .phoneNumber("976432132")
                                .role(Role.builder()
                                        .id(1L)
                                        .roleName("employee")
                                        .build())
                                .employer(Employer.builder()
                                        .id(1L)
                                        .name("Sri")
                                        .companyName("ideas2it")
                                        .description("it software skills development")
                                        .companyType("")
                                        .industryType("")
                                        .gender(GenderEnum.F)
                                        .build())
                                .build())
                        .gender(GenderEnum.F).build())
                .jobPost(JobPost.builder()
                        .id(1L)
                        .title("Front end")
                        .jobDescription("knowledge in angular")
                        .experience(3)
                        .keySkills(List.of("html", "css", "angular"))
                        .location(Location.builder()
                                .state("tamilnadu")
                                .city("chennai")
                                .build())
                        .jobCategory(JobCategory.builder()
                                .id(1L)
                                .name("it engineering")
                                .subcategories(Set.of(JobSubCategory.builder() // Create a Set
                                        .id(1L)
                                        .name("front end")
                                        .build()))
                                .build())
                        .employer(Employer.builder()
                                .id(1L)
                                .name("srisai")
                                .companyName("tcs")
                                .description("freshers hiring")
                                .companyType("service based")
                                .industryType("IT mnc")
                                .gender(GenderEnum.F).build())
                        .postedDate(LocalDate.of(2024, 8, 8))
                        .build())
                .build();

        jobApplicationDto = JobApplicationDto.builder()
                .id(1L)
                .status(JobApplicationStatus.APPLIED)
                .appliedDate(LocalDate.parse("2024-08-08").atStartOfDay())
                .employeeDto(EmployeeDto.builder()
                        .id(1L)
                        .name("John")
                        .dateOfBirth(LocalDate.of(2000, 10, 10))
                        .resume("resume")
                        .city("chennai")
                        .qualification("BE")
                        .yearOfExperience(3)
                        .currentCompany("ideas2it")
                        .designation("software developer")
                        .noticePeriod(3)
                        .keySkills(List.of("html", "css", "java"))
                        .gender(GenderEnum.F).build())
                .jobPostDto(JobPostDto.builder()
                        .id(1L)
                        .title("front end")
                        .jobDescription("knowledge in angular")
                        .experience(3)
                        .keySkills(List.of("html", "css", "angular"))
                        .state("tamilnadu")
                        .city("chennai")
                        .jobCategoryId(1L)
                        .jobSubCategoryId(1L)
                        .employerId(1L)
                        .postedDate(LocalDate.of(2024, 8, 8)).build())
                .build();
    }

    @Test
    void testGetAllJobApplications() {
        when(jobApplicationRepository.findAll()).thenReturn(List.of(jobApplication));
        List<JobApplicationDto> response = jobApplicationService.getAllJobApplications();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testGetJobApplicationById() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.ofNullable(jobApplication));
        JobApplicationDto response = jobApplicationService.getJobApplicationById(jobApplicationDto.getId());
        assertNotNull(response);
        assertEquals(jobApplicationDto.getAppliedDate(), response.getAppliedDate());
    }

    @Test
    void testGetJobApplicationByIdFailure() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobApplicationService.getJobApplicationById(jobApplicationDto.getId()));
    }

    @Test
    void testRemoveJobApplicationForEmployee() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.ofNullable(jobApplication));
        when(jobApplicationRepository.save(any(JobApplication.class))).thenReturn(jobApplication);
        String removeJobApplicationForEmployee = jobApplicationService.removeJobApplicationForEmployee(jobApplicationDto.getId());
        assertEquals("Job application Deleted Successfully", removeJobApplicationForEmployee);
    }

    @Test
    void testRemoveJobApplicationForEmployeeFailure() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobApplicationService.removeJobApplicationForEmployee(jobApplicationDto.getId()));
    }

    @Test
    void testUpdateApplicationStatus() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.ofNullable(jobApplication));
        when(jobApplicationRepository.save(any(JobApplication.class))).thenReturn(jobApplication);
        // when(emailService.sendEmail(employeeEmail, subject, message)).
        JobApplicationDto response = jobApplicationService.updateApplicationStatus(jobApplicationDto.getId(), String.valueOf(jobApplicationDto.getStatus()));
        assertNotNull(response);
        assertEquals(jobApplicationDto.getStatus(), response.getStatus());
    }

    @Test
    void testUpdateApplicationStatusFailure() {
        when(jobApplicationRepository.findById(jobApplicationDto.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jobApplicationService.updateApplicationStatus(jobApplicationDto.getId(), jobApplicationDto.getStatus().name()));
    }

    @Test
    void testGetJobApplicationByJobPostId() {
        when(jobApplicationRepository.findByJobPostId(jobApplicationDto.getJobPostDto().getId())).thenReturn(List.of(jobApplication));
        List<JobApplicationDto> response = jobApplicationService.getJobApplicationByJobPostId(jobApplicationDto.getJobPostDto().getId());
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testApplyJob() {
        when(jobApplicationRepository.findById(jobApplicationDto.getJobPostId())).thenReturn(Optional.ofNullable(jobApplication));
        when(jobApplicationRepository.save(jobApplication)).thenReturn(jobApplication);
        String response = jobApplicationService.applyJob(jobApplicationDto.getEmployeeId(), jobApplicationDto.getJobPostId());
        assertNotNull(response);
        assertEquals("Job Applied Successfully", response);
    }

    @Test
    void testApplyJobFailure() {
        lenient().when(jobPostService.retrieveJobForApplication(jobApplicationDto.getJobPostDto().getId())).thenReturn(null);
        lenient().when(employeeService.retrieveEmployeeForJobPost(jobApplicationDto.getEmployeeDto().getId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> jobApplicationService.applyJob(jobApplicationDto.getEmployeeDto().getId(), jobApplicationDto.getJobPostDto().getId()));
    }

    @Test
    void testRetrieveEmployeeAppliedJobs() {
        List<JobApplication> jobApplications = List.of(jobApplication);
        when(jobApplicationRepository.findByEmployeeId(1L)).thenReturn(jobApplications);

        List<JobApplicationDto> result = jobApplicationService.retrieveEmployeeAppliedJobs(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jobApplicationRepository, times(1)).findByEmployeeId(1L);
    }
    
    @Test
    void testRetrieveEmployeeAppliedJobsFailure() {
        when(jobApplicationRepository.findByEmployeeId(2L)).thenReturn(List.of());
        assertThrows(ResourceNotFoundException.class, () -> {
            jobApplicationService.retrieveEmployeeAppliedJobs(2L);
        });
    }
}
