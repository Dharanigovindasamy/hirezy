package com.ideas2it.hirezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.model.Employee;

/**
 * <p>
 *     This interface used for business logic like CRUD operations from the user input.
 *   Return as employeeDto after performing required operations
 *</p>
 * @author dharani.govindhasamy
 */
@Service
public interface EmployeeService {

     /**
      *Adding employee profile
      * @param employeeDto - {@link EmployeeDto} employeeDto data from user
      * @return employeeDto - employeeDto data from the table
      */
     EmployeeDto saveEmployee(EmployeeDto employeeDto) ;

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
      * </p>
      *  @param employeeId - employee Id of the employee user
      * @return employeeDto - employeeDto profile of employee
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
      * Deleting employee profile by giving employeeId
      *
      * @param employeeId - employeeId of the employee user
      */
     void deleteEmployee(Long employeeId);

     /**
      * This method is to retrieve employee for applying the job.
      * @param employeeId
      *    It is id of the employee who apply for the job
      * @return Employee
      *    It contains all the employee Details for applying Job.
      */
     Employee retrieveEmployeeForJobPost(long employeeId);

     /*
      * Return total register employee.
      */
     Long countEmployees();

}
