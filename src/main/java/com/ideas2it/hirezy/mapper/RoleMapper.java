package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.RoleDto;
import com.ideas2it.hirezy.model.Role;

public class RoleMapper {

    public static Role mapRoleDto(RoleDto roleDto) {
        return Role.builder()
                .ID(roleDto.getId())
                .roleName(roleDto.getRoleName())
                .build();
    }
}
