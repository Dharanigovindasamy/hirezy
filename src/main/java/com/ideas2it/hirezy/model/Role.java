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
    private Long ID;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
