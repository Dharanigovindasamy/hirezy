package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        employee = new Employee();
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
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        List<EmployeeDto> employees = employeeService.retrieveEmployees();

        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEmployeesWhenEmpty() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());

        List<EmployeeDto> employees = employeeService.retrieveEmployees();

        assertTrue(employees.isEmpty());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEmployeeById() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        EmployeeDto foundEmployee = employeeService.retrieveEmployeeById(employee.getId());

        assertNotNull(foundEmployee);
        assertEquals(employee.getId(), foundEmployee.getId());
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testRetrieveEmployeeByIdThrowsException() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.retrieveEmployeeById(employee.getId()));
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employeeDto.getId())).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto);

        assertNotNull(updatedEmployeeDto);
        assertEquals(employee.getId(), updatedEmployeeDto.getId());
        verify(employeeRepository, times(1)).findByIdAndIsDeletedFalse(employeeDto.getId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployeeThrowsException() {
        when(employeeRepository.findByIdAndIsDeletedFalse(employeeDto.getId())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(employeeDto));
        verify(employeeRepository, times(1)).findByIdAndIsDeletedFalse(employeeDto.getId());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employee.getId());

        assertTrue(employee.isDeleted());
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testDeleteEmployeeThrowsException() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employee.getId()));
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testRetrieveEmployeeForJobPost() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.retrieveEmployeeForJobPost(employee.getId());

        assertNotNull(foundEmployee);
        assertEquals(employee.getId(), foundEmployee.getId());
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testRetrieveEmployeeForJobPostThrowsException() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.retrieveEmployeeForJobPost(employee.getId()));
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testCountActiveEmployees() {
        when(employeeRepository.countByIsDeleted(false)).thenReturn(10L);

        Long activeEmployeeCount = employeeService.countActiveEmployees();

        assertEquals(10L, activeEmployeeCount);
        verify(employeeRepository, times(1)).countByIsDeleted(false);
    }

    @Test
    void testCountDeletedEmployees() {
        when(employeeRepository.countByIsDeleted(true)).thenReturn(5L);

        Long deletedEmployeeCount = employeeService.countDeletedEmployees();

        assertEquals(5L, deletedEmployeeCount);
        verify(employeeRepository, times(1)).countByIsDeleted(true);
    }
}
