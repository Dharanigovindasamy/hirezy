package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.model.Employee;

/**
 * <p>
 *     This class used for conversion of Dto to entity and vice-versa
 *
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */

public class EmployeeMapper {

    /**
     * <p>
     *     This method used to convert EmployeeDto profile into employee entity and set the values
     * @param employeeDto - employeeDto given from user
     * @return Employee - employee entity
     * </p.
     */
    public static Employee mapDtoToEntity (EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setResume(employeeDto.getResume());
        employee.setContactMail(employeeDto.getContactMail());
        employee.setCity(employeeDto.getCity());
        employee.setQualification(employeeDto.getQualification());
        employee.setPercentage(employeeDto.getPercentage());
        employee.setYearOfPassOut(employeeDto.getYearOfPassOut());
        employee.setWorkMode(employeeDto.getWorkMode());
        employee.setYearOfExperience(employeeDto.getYearOfExperience());
        employee.setCurrentCompany(employeeDto.getCurrentCompany());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setCompanyCity(employeeDto.getCompanyCity());
        employee.setNoticePeriod(employeeDto.getNoticePeriod());
        return employee;
    }

    /**
     * <p>
     *     This method used for converting entity into Dto to the user
     * </p>
     *
     * @param employee - employee entity to the user
     * @return EmployeeDto - EmployeeDto object to the user
     */
    public static EmployeeDto mapEntityToDto (Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setResume(employee.getResume());
        employeeDto.setContactMail(employee.getContactMail());
        employeeDto.setCity(employee.getCity());
        employeeDto.setQualification(employee.getQualification());
        employeeDto.setPercentage(employee.getPercentage());
        employeeDto.setYearOfPassOut(employee.getYearOfPassOut());
        employeeDto.setWorkMode(employee.getWorkMode());
        employeeDto.setYearOfExperience(employee.getYearOfExperience());
        employeeDto.setCurrentCompany(employee.getCurrentCompany());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setCompanyCity(employee.getCompanyCity());
        employeeDto.setNoticePeriod(employee.getNoticePeriod());
        return employeeDto;
    }
}

