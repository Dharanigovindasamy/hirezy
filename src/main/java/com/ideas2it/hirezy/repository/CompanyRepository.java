package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 *<p>This class is the interface to JPA repository and acts as the dao for CRUD
 * operations in the dao
 * </p>
@Author Audhithiyah
* @Version v1
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
