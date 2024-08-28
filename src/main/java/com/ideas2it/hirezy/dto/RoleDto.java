package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Enter role name")
    private String roleName;

}
