package com.ideas2it.hirezy.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.service.EmployeeService;

/**
 * <p>
 *  This class holds employee profile operations like adding, retrieving, updating and deleting
 *  The user data which can be sent as json format and stored as entity in the server table
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * <p>
     * Adding employee profile details into the table as HTTP json data format
     *
     * @param employeeDto - {@link EmployeeDto} employeeDto receive from user as json format
     * @return employeeDto - employeeDto after adding into the table
     * </p>
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    /**
     * <p>
     *     Retrieve all employee profile details from the table
     *
     * @return <List<EmployeeDto>> - list of employee profile
     * </p>
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayEmployees() {
        return new ResponseEntity<>(employeeService.retrieveEmployees(), HttpStatus.OK);
    }

    /**
     * <p>Display employee details By employee Id
     *
     * @param employeeId - Id of the employee
     * @return employeeDto - employeeDto of the respective employee Id
     * </p>
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeById(@PathVariable("id") Long employeeId) {
       EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *     Update employee by checking with employee id
     *
     * @param employeeId - employeeId of the employee
     * @param employeeDto - {@link EmployeeDto}
     * @return EmployeeDto -
     * if employee id not contains shows warning, otherwise it updates
     * If error occurs while updating, shows exception otherwise, it updates</p>
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        employeeDto = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>Delete employee by giving employee id
     *
     * @param employeeId - employeeId of the employee
     * </p>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
