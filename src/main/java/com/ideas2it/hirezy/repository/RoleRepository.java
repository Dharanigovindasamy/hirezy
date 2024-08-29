package com.ideas2it.hirezy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.hirezy.model.Role;

/**
 * This interface extends from CRUD repository performs CRUD operations
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * Find role details by giving role name
     * @param role - role name of the user
     * @return Role - role object of the given role
     */
    Role findRoleByRoleName(String role);
}
