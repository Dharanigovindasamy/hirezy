package com.ideas2it.hirezy.dto;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String emailId;
    private String password;
    private String phoneNumber;
    private String address;
}
