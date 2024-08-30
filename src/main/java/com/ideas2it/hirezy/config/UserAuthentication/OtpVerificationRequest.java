package com.ideas2it.hirezy.config.UserAuthentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     A request object for OTP verification containing email and OTP fields.
 *  *  The email address associated with the OTP. The OTP code to be verified
 * </p>
 *
 * @author Audhithiyah
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * This is the class that is used to obtain email
 * and otp from the user
 * @author audhithiyah
 */
public class OtpVerificationRequest {
    @NotBlank(message = "Email ID is Required")
    @Email(regexp = "\\\\b[A-za-z0-9._%-]\"\n\"+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}\\\\b")
    private String email;
    private String otp;
}
