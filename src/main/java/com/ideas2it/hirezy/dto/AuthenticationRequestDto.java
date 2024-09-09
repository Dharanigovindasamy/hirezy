package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        @Email(message = "Enter Valid mailId like example@gmail.com")
        private String emailId;
        @NotBlank(message = "Password is a mandatory field")
        private String password;
}
