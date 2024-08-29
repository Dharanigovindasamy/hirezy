package com.ideas2it.hirezy.config.UserAuthentication;

import com.ideas2it.hirezy.service.OtpService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This method is the controller that performs
 * operations such as register,login and verification
 */

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;

    /**
     * This is used to generate a otp and register a user
     * with the corresponding role
     * @param role
     * @param request
     * @return
     */
    @PostMapping("/register/{role}")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) throws MessagingException {
        String otp = otpService.generateOTP(request.getEmail());
        if (otp != null) {
            return ResponseEntity.ok(authenticationService.register(request, role));
        } else {
            AuthenticationResponse response = new AuthenticationResponse("Failed to generate OTP");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * This method is used to verify if the otp that
     * is sent and received are the same
     * @param request
     * @return
     */

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthenticationResponse> verifyOTP(@RequestBody OtpVerificationRequest request) {
        if (otpService.verifyOTP(request.getEmail(), request.getOtp())) {
            AuthenticationResponse response = new AuthenticationResponse("Account verified successfully");
            return ResponseEntity.ok(response);
        } else {
            AuthenticationResponse response = new AuthenticationResponse("Invalid OTP");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * This method is used to check if the account is already verified
     * and then generate its jwt token
     * @param request
     * @return
     */

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        if (!otpService.isAccountVerified(request.getEmail())) {
            AuthenticationResponse response = new AuthenticationResponse("Account not verified. Please verify your account.");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}