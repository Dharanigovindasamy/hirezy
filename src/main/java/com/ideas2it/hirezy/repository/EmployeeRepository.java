package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * This method is to retrieve all the employees from the database.
     */
    List<Employee> findByIsDeletedFalse();

    /**
     * Retrieve employee which is soft delete
     * @param employeeId - id of the employee
     * @return employee - employee are under the id
     */
    Employee findByIdAndIsDeletedFalse(long employeeId);

    /**
     * Counts the number of employees based on their deletion status.
     * @param isDeleted the deletion status to filter by,
     * @return the number of employees with the specified  deletion status.
     */
    Long countByIsDeleted(boolean isDeleted);


}
