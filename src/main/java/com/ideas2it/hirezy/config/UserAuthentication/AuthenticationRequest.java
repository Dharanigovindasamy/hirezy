package com.ideas2it.hirezy.config.UserAuthentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
 * @author audhithiyah
 */
public class AuthenticationRequest {
    @NotBlank(message = "Email ID is Required")
    @Email(regexp = "\\\\b[A-za-z0-9._%-]\"\n\"+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}\\\\b")
    private String email;
    @NotBlank(message = "password is a mandatory field")
    private String password;

}
