package com.ideas2it.hirezy.config.UserAuthentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Email ID is Required")
    @Email(regexp = "\\\\b[A-za-z0-9._%-]\"\n\"+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}\\\\b")
    private String email;

    @NotBlank(message = "Password must be required")
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "At least 8 chars\n" +
            "\n" +
            "Contains at least one digit\n" +
            "\n" +
            "Contains at least one lower alpha char and one upper alpha char\n" +
            "\n" +
            "Contains at least one char within a set of special chars (@#%$^ etc.)")
    private String password;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^\\+[1-9]\\d{1,4}\\d{10}$\n",message = "Invalid phone number. Please enter a number in the format +<country code><10-digit number>")
    private String phoneNumber;
}
