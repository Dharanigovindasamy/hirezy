package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.User;

/**
 * <p>
 *     This class used for conversion of Dto to entity and vice-versa
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */

public class EmployeeMapper {

    /**
     * <p>
     *     This method used to convert EmployeeDto profile into employee entity and set the values
     * @param employeeDto - {@link EmployeeDto}employeeDto given from user
     * @return Employee - employee entity
     * </p.
     */
    public static Employee mapDtoToEntity (EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setResume(employeeDto.getResume());
        employee.setCity(employeeDto.getCity());
        employee.setQualification(employeeDto.getQualification());
        employee.setYearOfExperience(employeeDto.getYearOfExperience());
        employee.setCurrentCompany(employeeDto.getCurrentCompany());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setNoticePeriod(employeeDto.getNoticePeriod());
        employee.setKeySkills(employeeDto.getKeySkills());
        return employee;
    }

    /**
     * <p>
     *     This method used for converting entity into Dto to the user
     * </p>
     *
     * @param employee - employee entity to the user
     * @return EmployeeDto - {@link EmployeeDto} EmployeeDto object to the user
     */
    public static EmployeeDto mapEntityToDto (Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setResume(employee.getResume());
        employeeDto.setCity(employee.getCity());
        employeeDto.setQualification(employee.getQualification());
        employeeDto.setYearOfExperience(employee.getYearOfExperience());
        employeeDto.setCurrentCompany(employee.getCurrentCompany());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setKeySkills(employee.getKeySkills());
        employeeDto.setNoticePeriod(employee.getNoticePeriod());
        return employeeDto;
    }

    public static Employee mapDtoToEntity (EmployeeDto employeeDto, User user) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setResume(employeeDto.getResume());
        employee.setCity(employeeDto.getCity());
        employee.setQualification(employeeDto.getQualification());
        employee.setYearOfExperience(employeeDto.getYearOfExperience());
        employee.setCurrentCompany(employeeDto.getCurrentCompany());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setNoticePeriod(employeeDto.getNoticePeriod());
        employee.setKeySkills(employeeDto.getKeySkills());
        employee.setUser(user);
        return employee;
    }
}

