package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.dto.JobSubCategoryDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.JobCategory;
import com.ideas2it.hirezy.model.JobPost;
import com.ideas2it.hirezy.model.JobSubCategory;
import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.repository.JobPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class JobPostServiceImplTest {
    @Mock
    private JobPostRepository jobPostRepository;

    @Mock
    private EmployerService employerService;

    @Mock
    private LocationService locationService;

    @Mock
    private JobCategoryService jobCategoryService;

    @Mock
    private JobSubCategoryService jobSubCategoryService;

    @InjectMocks
    private JobPostServiceImpl jobPostServiceImpl;

    private JobPost jobPost;
    private JobPostDto jobPostDto;
    private Employer employer;
    private EmployerDto employerDto;
    private JobCategory jobCategory;
    private JobCategoryDto jobCategoryDto;
    private JobSubCategory jobSubCategory;
    private JobSubCategoryDto jobSubCategoryDto;
    private Location location;

    @BeforeEach
    void setUp() {
        employerDto = new EmployerDto();
        employerDto.setId(1L);

        employer = new Employer();
        employer.setId(1L);

        jobCategoryDto = new JobCategoryDto();
        jobCategoryDto.setId(1L);

        jobCategory = new JobCategory();
        jobCategory.setId(1L);

        jobSubCategoryDto = new JobSubCategoryDto();
        jobSubCategoryDto.setId(1L);

        jobSubCategory = new JobSubCategory();
        jobSubCategory.setId(1L);

        location = new Location();
        location.setId(1L);

        jobPostDto = new JobPostDto();
        jobPostDto.setTitle("Software Developer");
        jobPostDto.setJobDescription("Java Developer");
        jobPostDto.setJobCategoryId(1L);
        jobPostDto.setJobSubCategoryId(1L);
        jobPostDto.setCity("Chennai");
        jobPostDto.setState("Tamil Nadu");

        jobPost = new JobPost();
        jobPost.setId(1L);
        jobPost.setTitle("Software Developer");
        jobPost.setJobDescription("Java Developer");
        jobPost.setEmployer(employer);
        jobPost.setLocation(location);
        jobPost.setJobCategory(jobCategory);
        jobPost.setJobSubCategory(jobSubCategory);
        jobPost.setPostedDate(LocalDate.now());
    }

    @Test
    void testGetAllJobs() {
        List<JobPost> jobPosts = List.of(jobPost);
        when(jobPostRepository.findAll()).thenReturn(jobPosts);

        List<JobPostDto> jobPostDtos = jobPostServiceImpl.getAllJobs();

        assertEquals(1, jobPostDtos.size());
        verify(jobPostRepository, times(1)).findAll();
    }

    @Test
    void testGetJobById_Found() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(jobPost));

        JobPostDto result = jobPostServiceImpl.getJobById(1L);

        assertNotNull(result);
        assertEquals(jobPost.getTitle(), result.getTitle());
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testGetJobById_NotFound() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> jobPostServiceImpl.getJobById(1L));
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testSearchJobsByFilters() {
        List<JobPost> jobPosts = List.of(jobPost);
        when(jobPostRepository.findAll(any(Specification.class))).thenReturn(jobPosts);

        Object[][] testCases = {
                {"Tamil Nadu", "Chennai", "IT", "Software Development", null, "Private", "IT", 2, List.of("Java")},
                {null, "Chennai", null, null, null, null, null, 2, null},
                {null, null, null, null, null, null, null, 2, null},
                {null, null, null, null, null, null, null, null, null},
                {"Tamil Nadu", "Chennai", null, null, null, null, null, null, List.of("Java", "Spring")}
        };
        for (Object[] testCase : testCases) {
            List<JobPostDto> jobPostDtos = jobPostServiceImpl.searchJobsByFilters(
                    (String) testCase[0], (String) testCase[1], (String) testCase[2],
                    (String) testCase[3], (String) testCase[4], (String) testCase[5],
                    (String) testCase[6], (Integer) testCase[7], (List<String>) testCase[8]);
            assertEquals(1, jobPostDtos.size());
        }
        verify(jobPostRepository, times(testCases.length)).findAll(any(Specification.class));
    }

    @Test
    void testRetrieveJobForApplication() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(jobPost));

        JobPost result = jobPostServiceImpl.retrieveJobForApplication(1L);

        assertNotNull(result);
        assertEquals(jobPost.getTitle(), result.getTitle());
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateJobPost() {
        when(employerService.getEmployerById(1L)).thenReturn(employerDto);
        when(jobCategoryService.getJobCategoryById(1L)).thenReturn(jobCategoryDto);
        when(jobSubCategoryService.getJobSubCategoryById(1L)).thenReturn(jobSubCategoryDto);
        when(locationService.findOrCreateLocation("Tamil Nadu", "Chennai")).thenReturn(location);
        when(jobPostRepository.save(any(JobPost.class))).thenReturn(jobPost);

        JobPostDto result = jobPostServiceImpl.createJobPost(1L, jobPostDto);

        assertNotNull(result);
        assertEquals(jobPost.getTitle(), result.getTitle());
        verify(jobPostRepository, times(1)).save(any(JobPost.class));
    }

    @Test
    void testUpdateJobPost_Found() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(jobPost));
        when(jobCategoryService.getJobCategoryById(1L)).thenReturn(jobCategoryDto);
        when(jobSubCategoryService.getJobSubCategoryById(1L)).thenReturn(jobSubCategoryDto);
        when(locationService.findOrCreateLocation("Tamil Nadu", "Chennai")).thenReturn(location);
        when(jobPostRepository.save(any(JobPost.class))).thenReturn(jobPost);

        JobPostDto result = jobPostServiceImpl.updateJobPost(1L, jobPostDto);

        assertNotNull(result);
        assertEquals(jobPost.getTitle(), result.getTitle());
        verify(jobPostRepository, times(1)).save(any(JobPost.class));
    }

    @Test
    void testUpdateJobPost_NotFound() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> jobPostServiceImpl.updateJobPost(1L, jobPostDto));
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteJobPost() {
        doNothing().when(jobPostRepository).deleteById(1L);

        jobPostServiceImpl.deleteJobPost(1L);

        verify(jobPostRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllJobPostsByEmployer() {
        List<JobPost> jobPosts = List.of(jobPost);
        when(jobPostRepository.findByEmployerId(1L)).thenReturn(jobPosts);

        List<JobPostDto> jobPostDtos = jobPostServiceImpl.getAllJobPostsByEmployer(1L);

        assertEquals(1, jobPostDtos.size());
        verify(jobPostRepository, times(1)).findByEmployerId(1L);
    }
}