package com.ideas2it.hirezy.config.UserAuthentication;

import com.ideas2it.hirezy.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ideas2it.hirezy.service.EmployerServiceImpl.logger;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;

    @PostMapping("/register/{role}")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) {
        String otp = otpService.generateOTP(request.getEmail());
        if (otp != null) {
            return ResponseEntity.ok(authenticationService.register(request, role));
        } else {
//            return ResponseEntity.badRequest().body("Failed to generate OTP");
            logger.warn("failed to generate otp");
            return null;
        }
    }

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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        // have to Check if user's account is verified
        if (!otpService.isAccountVerified(request.getEmail())) {
            AuthenticationResponse response = new AuthenticationResponse("Account not verified. Please verify your account.");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}