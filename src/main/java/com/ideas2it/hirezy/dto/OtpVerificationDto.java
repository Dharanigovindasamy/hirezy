package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   A request object for OTP verification containing email and OTP fields.
 *   The email address associated with the OTP. The OTP code to be verified
 * </p>
 *
 * @author Audhithiyah
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationDto {
        @NotBlank(message = "Email ID is Required")
        @Pattern(regexp = "\\\\b[A-za-z0-9._%-]\"\n\"+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}\\\\b")
        private String emailId;
        @NotBlank(message = "Enter valid or correct otp")
        private String otp;
        @NotBlank(message = "Password must be required")
        @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "At least 8 chars\n" +
                "\n" +
                "Contains at least one digit\n" +
                "\n" +
                "Contains at least one lower alpha char and one upper alpha char\n" +
                "\n" +
                "Contains at least one char within a set of special chars (@#%$^ etc.)")
        private String password;
}
