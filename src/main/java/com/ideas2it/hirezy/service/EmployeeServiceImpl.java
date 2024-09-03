package com.ideas2it.hirezy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.mapper.EmployeeMapper;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
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
    private  EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        User user = userService.retrieveUserById(employeeDto.getUserId());
        Employee employee = mapDtoToEntity(employeeDto,user);
        employeeDto = mapEntityToDto(employeeRepository.save(employee));
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees =  employeeRepository.findByIsDeletedFalse();

        if (employees.isEmpty()) {
            logger.warn("Empty employee details");
            throw new ResourceNotFoundException("Currently there is no Employee");
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
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId);

        if(null == employee) {
            logger.warn("No employee under this employee id {}", employeeId);
            throw new ResourceNotFoundException("Employee not found" + employeeId);
        }
        return mapEntityToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeDto.getId());
        if(null != employee) {
            employeeDto = mapEntityToDto(employeeRepository.save(EmployeeMapper.mapDtoToEntity(employeeDto)));
            logger.info("Employee details updated successfully {}", employeeDto.getId());
            return employeeDto;
        }
        logger.warn("No employee under in employee id {}", employeeDto.getId());
        throw new ResourceNotFoundException("Employee not found" + employeeDto.getId());
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId);
        if (null == employee) {
            logger.warn("No employee found in employee id {}", employeeId);
            throw new ResourceNotFoundException("Employee not found" + employeeId);
        }
        employee.setDeleted(true);
        employeeRepository.save(employee);
        logger.info("Employee id deleted successfully {} ", employeeId);
    }

    @Override
    public Employee retrieveEmployeeForJobPost(long employeeId) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId);
        if(employee == null) {
            throw new ResourceNotFoundException("Employee Id - " + employeeId + " not found");
        }
        return employee;
    }

    @Override
    public Long countActiveEmployees() {
        return employeeRepository.countByIsDeleted(false);
    }

    @Override
    public Long countDeletedEmployees() {
        return employeeRepository.countByIsDeleted(true);
    }
}
