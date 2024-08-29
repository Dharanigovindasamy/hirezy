package com.ideas2it.hirezy.config.UserAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * This is the class that is used to obtain the request from
 * the user
 */
public class AuthenticationRequest {
    private String email;
    private String password;

}
