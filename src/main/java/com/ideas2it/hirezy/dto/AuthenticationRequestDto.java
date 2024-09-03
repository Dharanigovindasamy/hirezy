package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the class that is used to obtain the request from
 * the user
 * @author audhithiyah
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
        @NotBlank(message = "Email ID is Required")
        @Email(regexp = "\\\\b[A-za-z0-9._%-]\"\n\"+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}\\\\b")
        private String emailId;
        @NotBlank(message = "password is a mandatory field")
        private String password;
}
