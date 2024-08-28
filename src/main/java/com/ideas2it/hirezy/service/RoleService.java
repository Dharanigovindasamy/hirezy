package com.ideas2it.hirezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.repository.RoleRepository;

/**
 * This class is the Service class for role.
 * @author paari
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * This method is used to add roles in the database.
     * It will run once the application is started.
     */
    public void addRoles(){
        if(roleRepository.findById(1L).isEmpty()) {
            roleRepository.save(Role.builder().roleName("ROLE_ADMIN").build());
            roleRepository.save(Role.builder().roleName("ROLE_EMPLOYER").build());
            roleRepository.save(Role.builder().roleName("ROLE_EMPLOYEE").build());
        }
    }

    /**
     * This method is to get roles for the employee according to the request.
     * @param roleName
     *     It contains the role of the user who send a request.
     * @return Role
     *     It will return the Role with role name and the Id for that user.
     */
    public Role retrieveRoleByName(String roleName) {
        return roleRepository.findRoleByRoleName("ROLE_"+roleName.toUpperCase());
    }
}
