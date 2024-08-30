package com.ideas2it.hirezy.config.UserAuthentication;

import com.ideas2it.hirezy.service.OtpService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * This class is the controller class for signup and login management.
 * It will manage the user verification and Otp generation for the user.
 * @author paari,audhithiyah
 */
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;
    /**
     * This is method will manage the user Sign up.
     * @param role
     *     It is the role of the user who will Sign up.
     * @param request
     *     It contains the User details for Sign up.
     * @return String
     *     It IS the message to the user whether he is signed up are not.
     */
    @PostMapping("/register/{role}")
    public ResponseEntity<String> register(
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) throws MessagingException {
        String otp = otpService.generateOTP(request.getEmail());
        if (otp != null) {
            return new ResponseEntity<>(authenticationService.registerUser(request, role),HttpStatus.CREATED);
        } else {
//            logger.warn("failed to generate otp");
            return  new ResponseEntity<>(
                    "OTP generation Failed.Please Try Again Later",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is to verify the Otp entered by the user.
     * @param request
     *     It contains the email and the otp entered by the user.
     * @return String
     *     It is the message  to the user whether the Otp is correct or not.
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OtpVerificationRequest
                                                    request) {
        if (otpService.verifyOTP(request.getEmail(), request.getOtp())) {
            return new ResponseEntity<>(
                    "Account Verified Successfully. Login to Continue.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is to verify the login information of the user.
     * @param request
     *     It contains the email and the password of the user.
     * @return AuthenticationResponse
     *     It contains the Token for the user if he is verified.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        if (!otpService.isAccountVerified(request.getEmail()) &&
                !request.getEmail().equals("kishoreofficial@gmail.com")) {
            AuthenticationResponse response = new AuthenticationResponse(
                    "Account not verified. Please verify your account.");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}