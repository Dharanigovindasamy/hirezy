package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
        @Email
        private String emailId;
        @NotBlank(message = "Enter valid or correct otp")
        private String otp;

}
