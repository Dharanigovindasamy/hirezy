package com.ideas2it.hirezy.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * This is the Dto class for Role.
 * It contains the roleId and roleName.
 * @author paari
 */
@Builder
@Getter
public class RoleDto {

    private long id;
    private String roleName;

}
