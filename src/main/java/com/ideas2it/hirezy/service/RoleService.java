package com.ideas2it.hirezy.service;


import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void addRoles(){
        if(roleRepository.findById(1L).isEmpty()) {
            roleRepository.save(Role.builder().roleName("ROLE_ADMIN").build());
            roleRepository.save(Role.builder().roleName("ROLE_EMPLOYER").build());
            roleRepository.save(Role.builder().roleName("ROLE_EMPLOYEE").build());
        }
    }

    public Role retrieveRoleByName(String roleName) {
        return roleRepository.findRoleByRoleName("ROLE_"+roleName.toUpperCase());
    }
}
