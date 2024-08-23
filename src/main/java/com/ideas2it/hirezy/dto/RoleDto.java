package com.ideas2it.hirezy.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * This is the Dto class for Role.
 * It contains the roleId and roleName.
 */
@Builder
@Getter
public class RoleDto {

    private Long id;
    private  String roleName;

}
