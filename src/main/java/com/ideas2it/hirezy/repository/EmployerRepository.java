package com.ideas2it.hirezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.Employer;

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
     *This method is used to display list of  Employer that are found to be soft deleted
     * @return list of employers
     * </p>
     */
    List<Employer> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns employer that is soft deleted
     * @param companyId - id of the company
     * @return Employer - employer who works in the company
     * </p>
     */
    Employer findByIsDeletedFalseAndId(long companyId);


}
