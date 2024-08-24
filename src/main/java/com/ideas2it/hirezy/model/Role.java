package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * This is the Role class which will define the user role.
 * @author paari
 */
@Entity
@Table(name = "Role")
@Builder
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

}