package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *This class is the interface to JPA repository and acts as
 * The dao for CRUD operations in the dao
 * @author Audhithiyah
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

    /**
     * Counts the number of employer based on their deletion status.
     * @param isDeleted the deletion status to filter by,
     * @return the number of employers with the specified  deletion status.
     */
    Long countByIsDeleted(boolean isDeleted);


}
