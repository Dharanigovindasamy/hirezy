package com.ideas2it.hirezy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.dto.JobPostDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.EmployerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {
    @Mock
 private EmployerRepository employerRepository;
    @Mock
    private UserService userService;

    @Mock
    private JobPostService jobPostService;

    @InjectMocks
    private EmployerServiceImpl employerService;
    private Employer employer;
    private EmployerDto employerDto;
    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setId(1L);

        employer = new Employer();
        employer.setId(1L);
        employer.setUser(user);
        employer.setName("ABC Company");

        employerDto = new EmployerDto();
        employerDto.setId(1L);
        employerDto.setName("ABC Company");
        employerDto.setUserId(1L);
    }
    @Test
    public void testCreateEmployer() {
        when(userService.retrieveUserById(anyLong())).thenReturn(user);
        when(employerRepository.save(any(Employer.class))).thenReturn(employer);
//        when(EmployerMapper.convertDtoToEntity(any(EmployerDto.class), any(User.class))).thenReturn(employer);
//        when(EmployerMapper.convertEntityToDto(any(Employer.class))).thenReturn(employerDto);

        EmployerDto savedEmployerDto = employerService.createEmployer(employerDto);
        assertNotNull(savedEmployerDto);
        assertEquals(employerDto.getId(), savedEmployerDto.getId());
    }
    @Test
    public void testGetAllEmployers() {
        List<Employer> employers = new ArrayList<>();
        employers.add(employer);
        when(employerRepository.findByIsDeletedFalse()).thenReturn(employers);
//        when(EmployerMapper.convertEntityToDto(any(Employer.class))).thenReturn(employerDto);

        List<EmployerDto> employerDtos = employerService.getAllEmployers();
        assertNotNull(employerDtos);
        assertEquals(1, employerDtos.size());
    }
    @Test
    public void testRemoveEmployer() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(employer);

        employerService.removeEmployer(1L);
        assertTrue(employer.isDeleted());
        verify(employerRepository, times(1)).save(employer);
    }

    @Test
    public void testRemoveEmployerNotFound() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> employerService.removeEmployer(1L));
    }

    @Test
    public void testUpdateEmployer() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(employer);
        when(employerRepository.save(any(Employer.class))).thenReturn(employer);
//        when(EmployerMapper.convertEntityToDto(any(Employer.class))).thenReturn(employerDto);
//        when(EmployerMapper.convertDtoToEntity(any(EmployerDto.class))).thenReturn(employer);

        EmployerDto updatedEmployer = employerService.updateEmployer(employerDto);
        assertNotNull(updatedEmployer);
        assertEquals(employerDto.getId(), updatedEmployer.getId());
    }

    @Test
    public void testUpdateEmployerNotFound() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> employerService.updateEmployer(employerDto));
    }

    @Test
    public void testGetEmployerById() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(employer);
//        when(EmployerMapper.convertEntityToDto(any(Employer.class))).thenReturn(employerDto);

        EmployerDto retrievedEmployer = employerService.getEmployerById(1L);
        assertNotNull(retrievedEmployer);
        assertEquals(employerDto.getId(), retrievedEmployer.getId());
    }

    @Test
    public void testGetEmployerByIdNotFound() {
        when(employerRepository.findByIsDeletedFalseAndId(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> employerService.getEmployerById(1L));
    }

    @Test
    public void testUpdateJobPost() {
        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setId(1L);

        when(jobPostService.updateJobPost(anyLong(), any(JobPostDto.class))).thenReturn(jobPostDto);

        JobPostDto updatedJobPost = employerService.updateJobPost(1L, 1L, jobPostDto);
        assertNotNull(updatedJobPost);
        assertEquals(jobPostDto.getId(), updatedJobPost.getId());
    }

    @Test
    public void testDeleteJobPost() {
        doNothing().when(jobPostService).deleteJobPost(anyLong());

        employerService.deleteJobPost(1L);

        verify(jobPostService, times(1)).deleteJobPost(1L);
    }

    @Test
    public void testGetAllJobPostsByEmployer() {
        List<JobPostDto> jobPostDtos = new ArrayList<>();
        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setId(1L);
        jobPostDtos.add(jobPostDto);

        when(jobPostService.getAllJobPostsByEmployer(anyLong())).thenReturn(jobPostDtos);

        List<JobPostDto> retrievedJobPosts = employerService.getAllJobPostsByEmployer(1L);
        assertNotNull(retrievedJobPosts);
        assertEquals(1, retrievedJobPosts.size());
    }

    @Test
    public void testCountActiveEmployers() {
        when(employerRepository.countByIsDeleted(false)).thenReturn(5L);
        Long count = employerService.countActiveEmployers();
        assertEquals(5L, count);
    }

    @Test
    public void testCountDeletedEmployers() {
        when(employerRepository.countByIsDeleted(true)).thenReturn(2L);
        Long count = employerService.countDeletedEmployers();
        assertEquals(2L, count);
    }
}