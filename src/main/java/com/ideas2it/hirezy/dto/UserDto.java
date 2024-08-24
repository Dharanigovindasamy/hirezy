package com.ideas2it.hirezy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This is the User Dto class which contains Username, Password,PhoneNumber.
 * @author paari
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String userName;
    private String emailId;
    private String password;
    private String phoneNumber;
}
