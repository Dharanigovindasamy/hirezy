package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.EmployeeProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfiles, Long> {

}
