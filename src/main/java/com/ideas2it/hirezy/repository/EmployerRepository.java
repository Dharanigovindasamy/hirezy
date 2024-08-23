package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *<p>This class is the interface to JPA repository and acts as the dao for CRUD
 * operations in the dao
 * </p>
@Author Audhithiyah
* @Version v1
 */
@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    /**
     * <p>
     *This method is used to display departments that are found to be soft deleted
     * </p>
     */
    List<Employer> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns a single department that is soft deleted
     * </p>
     */
    Employer findByIsDeletedFalseAndId(int companyId);

    /**
     * <p>
     *This method is used to check the repository if the name already exist
     * </p>
     */
    public boolean existsByCompanyName(String departmentName) ;

}
