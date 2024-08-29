package com.ideas2it.hirezy.config.UserAuthentication;

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
public class OtpVerificationRequest {
    private String email;
    private String otp;
}
