package com.ideas2it.hirezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.Employee;

/**
 * <p>
 *     This interface extends from Jpa repository which can perform CRUD operations
 * and stored in the database table
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Retrieve employee which is soft delete
     * @param employeeId - id of the employee
     * @return employee - employee are under the id
     */
    Employee findByIdAndIsDeletedFalse(long employeeId);

}
