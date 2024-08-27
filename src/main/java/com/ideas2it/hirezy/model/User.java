package com.ideas2it.hirezy.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  This class is the user entity class for the application Hirezy.
 *  It manages all the user login details such as username, emailId,
 *  Password, PhoneNumber.
 *  This is used to Authenticate and authorize the user.
 * </p>
 * @author paari
 */
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String userName;

    @Column(name = "email")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



}
