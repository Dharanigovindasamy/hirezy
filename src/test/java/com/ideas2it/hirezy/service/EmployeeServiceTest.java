package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.model.enums.Gender;
import com.ideas2it.hirezy.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;
    private User user;

    @BeforeEach
    void setUp() {
        employee = (Employee.builder()
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
                                .gender(Gender.F)
                                .build())
                        .build())
                .gender(Gender.F)
                .build());

        employee.setId(1L);
        employee.setDeleted(false);

        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setUserId(1L);

        user = new User();
        user.setId(1L);
    }

    @Test
    void testSaveEmployee() {
        when(userService.retrieveUserById(employeeDto.getUserId())).thenReturn(user);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        assertNotNull(savedEmployeeDto);
        assertEquals(employee.getId(), savedEmployeeDto.getId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testRetrieveEmployees() {
        lenient().when(employeeRepository.findByIsDeletedFalse()).thenReturn(Arrays.asList(employee));
        List<EmployeeDto> employees = employeeService.retrieveEmployees();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
    }


    @Test
    void testRetrieveEmployeeById() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(employee);
        EmployeeDto foundEmployee = employeeService.retrieveEmployeeById(employee.getId());
        assertNotNull(foundEmployee);
        assertEquals(employee.getId(), foundEmployee.getId());
    }

    @Test
    void testRetrieveEmployeeByIdThrowsException() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> employeeService.retrieveEmployeeById(employee.getId()));
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.existsById(employeeDto.getId())).thenReturn(true);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto);
        assertNotNull(updatedEmployeeDto);
        assertEquals(employee.getId(), updatedEmployeeDto.getId());
    }

    @Test
    void testUpdateEmployeeThrowsException() {
        when(employeeRepository.existsById(employeeDto.getId())).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(employeeDto));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(employee);
        employeeService.deleteEmployee(employee.getId());
        assertTrue(employee.isDeleted());
    }

    @Test
    void testDeleteEmployeeThrowsException() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employee.getId()));
    }

    @Test
    void testRetrieveEmployeeForJobPost() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(employee);
        Employee foundEmployee = employeeService.retrieveEmployeeForJobPost(employee.getId());
        assertNotNull(foundEmployee);
        assertEquals(employee.getId(), foundEmployee.getId());
    }

    @Test
    void testRetrieveEmployeeForJobPostThrowsException() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employee.getId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> employeeService.retrieveEmployeeForJobPost(employee.getId()));
    }

    @Test
    void testCountActiveEmployees() {
        when(employeeRepository.countByIsDeleted(false)).thenReturn(10L);
        Long activeEmployeeCount = employeeService.countActiveEmployees();
        assertEquals(10L, activeEmployeeCount);
    }

    @Test
    void testCountDeletedEmployees() {
        when(employeeRepository.countByIsDeleted(true)).thenReturn(5L);
        Long deletedEmployeeCount = employeeService.countDeletedEmployees();
        assertEquals(5L, deletedEmployeeCount);
        verify(employeeRepository, times(1)).countByIsDeleted(true);
    }
}
