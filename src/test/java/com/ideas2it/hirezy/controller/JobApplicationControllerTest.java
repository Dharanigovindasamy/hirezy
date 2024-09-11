package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.*;
import com.ideas2it.hirezy.model.enums.Gender;
import com.ideas2it.hirezy.model.enums.JobApplicationStatus;
import com.ideas2it.hirezy.service.JobApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobApplicationControllerTest {

    @Mock
    private JobApplicationService jobApplicationService;

    @InjectMocks
    private JobApplicationController jobApplicationController;

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
                        .city("chennai")
                        .qualification("BE")
                        .yearOfExperience(3)
                        .currentCompany("ideas2it")
                        .designation("software developer")
                        .noticePeriod(3)
                        .keySkills(List.of("html", "css", "java")) // Corrected the keySkills list
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
                                        .gender(Gender.F)
                                        .build())
                                .build())
                        .gender(Gender.F).build())
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
                                .subCategories(Set.of(JobSubCategory.builder() // Create a Set
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
                                .gender(Gender.F).build())
                        .postedDate(LocalDate.of(2024, 8, 8))
                        .build())
                .build();

        jobApplicationDto = JobApplicationDto.builder()
                .id(1L)
                .status(JobApplicationStatus.APPLIED)
                .appliedDate(LocalDate.parse("2024-08-08").atStartOfDay()) // Corrected line
                .employeeDto(EmployeeDto.builder()
                        .id(1L)
                        .name("John")
                        .dateOfBirth(LocalDate.of(2000, 10, 10))
                        .city("chennai")
                        .qualification("BE")
                        .yearOfExperience(3)
                        .currentCompany("ideas2it")
                        .designation("software developer")
                        .noticePeriod(3)
                        .keySkills(List.of("html", "css", "java"))
                        .gender(Gender.F).build())
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
    void testApplyJobApplication() {
        String result = " ";
        when(jobApplicationService.applyJob(jobApplicationDto.getJobPostId(), jobApplicationDto.getEmployeeId())).thenReturn(result);
        ResponseEntity<String> response = jobApplicationController.applyForJob(jobApplicationDto.getJobPostId(), jobApplicationDto.getEmployeeId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllApplications() {
        when(jobApplicationService.getAllJobApplications()).thenReturn(List.of(jobApplicationDto));
        ResponseEntity<List<JobApplicationDto>> response = jobApplicationController.getAllJobApplications();
        assertEquals(1,response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetJobApplicationById() {
        when(jobApplicationService.getJobApplicationById(jobApplicationDto.getId())).thenReturn(jobApplicationDto);
        ResponseEntity<JobApplicationDto> response = jobApplicationController.getJobApplicationById(jobApplicationDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobApplicationDto.getAppliedDate(), response.getBody().getAppliedDate());
    }

    @Test
    void testRemoveJobApplication() {
        String removeJob = " ";
        when(jobApplicationService.removeJobApplicationForEmployee(jobApplicationDto.getId())).thenReturn(removeJob);
        ResponseEntity<String> response = jobApplicationController.removeEmployeeJobApplication(jobApplicationDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateApplicationStatus() {
        when(jobApplicationService.updateApplicationStatus(jobApplicationDto.getId(), String.valueOf(jobApplicationDto.getStatus()))).thenReturn(jobApplicationDto);
        ResponseEntity<JobApplicationDto> response = jobApplicationController.updateApplicationStatus(jobApplicationDto.getId(), String.valueOf(jobApplicationDto.getStatus()));
        assertEquals(jobApplicationDto.getStatus(), response.getBody().getStatus());
    }

    @Test
    void testGetJobApplicationByJobPostId() {
        List<JobApplicationDto> jobApplicationList = Collections.singletonList(jobApplicationDto);
        when(jobApplicationService.getJobApplicationByJobPostId(jobApplicationDto.getJobPostId())).thenReturn(jobApplicationList);
        ResponseEntity<List<JobApplicationDto>> response = jobApplicationController.getJobApplicationByJobPostId(jobApplicationDto.getJobPostId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetJobApplicationByJobPostIdFailure() {
        when(jobApplicationService.getJobApplicationByJobPostId(jobApplicationDto.getJobPostId())).thenReturn(Collections.emptyList());
        ResponseEntity<List<JobApplicationDto>> response = jobApplicationController.getJobApplicationByJobPostId(jobApplicationDto.getJobPostId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetJobApplicationByEmployee() {
        List<JobApplicationDto> jobApplicationList = Collections.singletonList(jobApplicationDto);
        when(jobApplicationService.retrieveEmployeeAppliedJobs(jobApplicationDto.getEmployeeId())).thenReturn(jobApplicationList);
        ResponseEntity<List<JobApplicationDto>> response = jobApplicationController.getJobApplicationByEmployee(jobApplicationDto.getEmployeeId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(jobApplicationDto.getStatus(), response.getBody().get(0).getStatus());
    }
}
