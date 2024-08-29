package com.ideas2it.hirezy.config.UserAuthentication;

import com.ideas2it.hirezy.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ideas2it.hirezy.service.EmployerServiceImpl.logger;

/**
 *  <p>
 *      This class used for check authentication while registering through role and mail id
 *  It can register and login the user information for authentication and authorization process
 *  </p>
 *
 * @author
 * @version 1
 */
@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;

    /**
     * <p>
     *     Register the user by giving role and user information like mail id, password
     * </p>
     * @param role - role of the user
     * @param request - user registering context
     * @return AuthenticationResponse - getting response after registering
     */
    @PostMapping("/register/{role}")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) {
        String otp = otpService.generateOTP(request.getEmail());
        if (otp != null) {
            return ResponseEntity.ok(authenticationService.register(request, role));
        } else {
            logger.warn("failed to generate otp");
            return null;
        }
    }

    /**
     * <p>
     *     Verify the mail by sending OTP to the mail id and verify the account is registered or not
     * </p>
     * @param request - verify context like mail, otp
     * @return AuthenticationResponse - after verification with otp return status either verified or not
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
     * <p>
     *     Login the account which is registered
     * </p>
     *
     * @param request - login mail and password after register
     * @return AuthenticationResponse - login authentication either login ot not
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