package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.mapper.CompanyMapper;
import com.ideas2it.hirezy.model.Company;
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
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * <p>
     *This method is used to display departments that are found to be soft deleted
     * </p>
     */
    List<Company> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns a single department that is soft deleted
     * </p>
     */
    Company findByIsDeletedFalseAndId(int companyId);

    /**
     * <p>
     *This method is used to check the repository if the name already exist
     * </p>
     */
    public boolean existsByCompanyName(String departmentName) ;

}
