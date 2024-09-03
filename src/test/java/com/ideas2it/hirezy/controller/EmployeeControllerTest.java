package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Kishore");
        employeeDto.setUserId(1L);
    }

    @Test
    void testAddEmployee() {
        when(employeeService.saveEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.addEmployee(employeeDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
        verify(employeeService, times(1)).saveEmployee(employeeDto);
    }

    @Test
    void testDisplayEmployees() {
        List<EmployeeDto> employeeDtos = Arrays.asList(employeeDto);
        when(employeeService.retrieveEmployees()).thenReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> response = employeeController.displayEmployees();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDtos, response.getBody());
        verify(employeeService, times(1)).retrieveEmployees();
    }

    @Test
    void testDisplayEmployeeById() {
        when(employeeService.retrieveEmployeeById(1L)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.displayEmployeeById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
        verify(employeeService, times(1)).retrieveEmployeeById(1L);
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(employeeDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
        verify(employeeService, times(1)).updateEmployee(employeeDto);
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(1L);
        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}