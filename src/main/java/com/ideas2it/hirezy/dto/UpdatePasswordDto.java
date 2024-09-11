package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdatePasswordDto {
    @NotBlank(message = "Email ID is Required")
    @Email
    private String emailId;
    @NotBlank(message = "Enter valid or correct otp")
    private String otp;
    @NotNull
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "At least 8 chars\n" +
            "\n" +
            "Contains at least one digit\n" +
            "\n" +
            "Contains at least one lower alpha char and one upper alpha char\n" +
            "\n" +
            "Contains at least one char within a set of special chars (@#%$^ etc.)")
    private String password;
}
