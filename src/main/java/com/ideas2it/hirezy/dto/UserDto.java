package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Email ID is Required")
    @Email(message = "Enter Valid mailId like example@gmail.com")
    private String emailId;

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
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Invalid phone number. Please enter a number in the format <10-digit number>")
    private String phoneNumber;
}
