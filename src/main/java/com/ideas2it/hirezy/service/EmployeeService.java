package com.ideas2it.hirezy.service;

import java.util.List;

import com.ideas2it.hirezy.dto.EmployeeDto;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     This interface used for business logic like CRUD operations from the user input.
 *   Return as employeeDto after performing required operations
 *
 * </p>
 *
 * @author dharani.govindhasamy
 */
@Service
public interface EmployeeService {

     /**
      * <p>Adding employee profile
      *
      * @param employeeDto - {@link EmployeeDto} employeeDto data from user
      * @return employeeDto - employeeDto data from the table
      * </p>
      */
     EmployeeDto saveEmployee(EmployeeDto employeeDto);

     /**
      * <p>
      *     Retrieving all employees
      *
      *  @return List<EmployeeDto> -list of employeeDto profiles
      * </p>
      */
     List<EmployeeDto> retrieveEmployees();

     /**
      * <p>
      *     Retrieving employee profile details by providing employeeId
      *
      *  @param employeeId - employee Id of the employee user
       * @return employeeDto - employeeDto profile of employee
      * </p>
      *
      */
     EmployeeDto retrieveEmployeeById(Long employeeId);

     /**
      * <p>
      *     Updating employee profile by giving employeeId
      *
      * @param employeeDto - {@link EmployeeDto} employeeDto data which to be updated
      * @return employeeDto - updated Employee profiles sent to the user
      * </p>
      */
     EmployeeDto updateEmployee(EmployeeDto employeeDto);

     /**
      * <p>
      *     Deleting employee profile by giving employeeId
      *
      * @param employeeId - employeeId of the employee user
      * </p>*
      */
     void deleteEmployee(Long employeeId);
}
