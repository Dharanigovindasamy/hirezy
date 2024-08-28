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

    Employee findByIdAndIsDeletedFalse(long employeeId);

}
