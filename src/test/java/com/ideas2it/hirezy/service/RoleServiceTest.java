package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.model.Location;
import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testAddRoles() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());
        when(roleRepository.save(any(Role.class))).thenReturn(new Role());
        roleService.addRoles();
        verify(roleRepository, times(3)).save(any(Role.class));
    }

    @Test
    public void testRetrieveRoleByName() {
        String roleName = "ADMIN";
        Role role = Role.builder().roleName("ROLE_ADMIN").build();
        when(roleRepository.findRoleByRoleName("ROLE_ADMIN")).thenReturn(role);
        Role result = roleService.retrieveRoleByName(roleName);
        assertNotNull(result);
        assertEquals("ROLE_ADMIN", result.getRoleName());
    }

    @Test
    public void testRetrieveRoleByNameNotFound() {
        String roleName = "INVALID";
        when(roleRepository.findRoleByRoleName("ROLE_INVALID")).thenReturn(null);
        Role result = roleService.retrieveRoleByName(roleName);
        assertEquals(null, result);
    }
}