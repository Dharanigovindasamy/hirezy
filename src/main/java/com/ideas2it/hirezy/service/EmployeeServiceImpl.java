package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.repository.EmployeeRepository;
import static com.ideas2it.hirezy.mapper.EmployeeMapper.mapDtoToEntity;
import static com.ideas2it.hirezy.mapper.EmployeeMapper.mapEntityToDto;

/**
 * <p>
 *     This class used for business logic of job applicants profile like CRUD operations in employee ,etc..
 *   and convert the json data into http data
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger logger = LogManager.getLogger();

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapDtoToEntity(employeeDto);
        employeeDto = mapEntityToDto(employeeRepository.save(employee));
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees =  employeeRepository.findAll();
        if (employees.isEmpty()) {
            logger.warn("Empty employee details");
        } else {
            for(Employee employee : employees) {
                EmployeeDto employeeDto = mapEntityToDto(employee);
                employeeDtos.add(employeeDto);
            }
        }
        return employeeDtos;
    }


    @Override
    public EmployeeDto retrieveEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found" + employeeId));

        if(null == employee) {
            logger.warn("No employee under this employee id {}", employeeId);
            return null;
        }
            return mapEntityToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        EmployeeDto finalEmployeeDto = employeeDto;
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found" + finalEmployeeDto.getId()));
        if(null == employee) {
            logger.warn("No employee under in employee id {}", employeeDto.getId());
            return null;
        } else {
            employee.setDateOfBirth(employeeDto.getDateOfBirth());
            employee.setResume(employeeDto.getResume());
            employee.setName(employeeDto.getName());
            employee.setCity(employeeDto.getCompanyCity());
            employee.setQualification(employeeDto.getQualification());
            employee.setPercentage(employeeDto.getPercentage());
            employee.setYearOfPassOut(employeeDto.getYearOfPassOut());
            employee.setWorkMode(employeeDto.getWorkMode());
            employee.setYearOfExperience(employeeDto.getYearOfExperience());
            employee.setCurrentCompany(employeeDto.getCurrentCompany());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setCompanyCity(employeeDto.getCompanyCity());
            employee.setNoticePeriod(employeeDto.getNoticePeriod());
            employeeDto = mapEntityToDto(employeeRepository.save(employee));
            logger.info("Employee details updated successfully {}", employeeDto.getId());
        }
        return employeeDto;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found" + employeeId));
        if (null == employee) {
            logger.warn("No employee found in employee id {}", employeeId);
        }
        employee.setRemoved(true);
        employeeRepository.save(employee);
        logger.info("Employee id deleted successfully {} ", employeeId);
    }
}
