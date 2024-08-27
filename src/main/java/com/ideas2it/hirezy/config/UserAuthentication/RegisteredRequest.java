package com.ideas2it.hirezy.config.UserAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredRequest {
    private String name;

    private String email;

    private String password;

    private String phoneNumber;
}
