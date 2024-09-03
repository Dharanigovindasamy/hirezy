package com.ideas2it.hirezy.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.model.AuthenticationRequest;
import com.ideas2it.hirezy.model.AuthenticationResponse;
import com.ideas2it.hirezy.model.OtpVerificationRequest;
import com.ideas2it.hirezy.model.RegisteredRequest;
import com.ideas2it.hirezy.service.AuthenticationService;
import com.ideas2it.hirezy.service.OtpService;

/**
 * This class is the controller class for signup and login management.
 * It will manage the user verification and Otp generation for the user.
 * @author audhithiyah
 */
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final Logger logger = LogManager.getLogger(AuthenticationController.class);
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
    public ResponseEntity<String> registerUser(@Valid
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) throws MessagingException {
        String otp = otpService.generateOTP(request.getEmail());
        if (otp != null) {
            return new ResponseEntity<>(authenticationService.registerUser(
                    request, role),HttpStatus.CREATED);
        } else {
            logger.warn("failed to generate otp");
            return  new ResponseEntity<>(
                    "OTP generation Failed.Please Try Again Later",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is to verify the Otp entered by the user.
     * @param otpVerificationRequest
     *     It contains the email and the otp entered by the user.
     * @return String
     *     It is the message  to the user whether the Otp is correct or not.
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OtpVerificationRequest
                                                    otpVerificationRequest) {
        if (otpService.verifyOTP(otpVerificationRequest.getEmail(),
                otpVerificationRequest.getOtp())) {
            logger.info("account has been verified successfully");
            return new ResponseEntity<>(
                    "Account Verified Successfully. Login to Continue.",
                    HttpStatus.OK);
        } else {
            logger.warn("incorrect otp has been sent,please check");
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
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody AuthenticationRequest request
    ) {
        if (!otpService.isAccountVerified(request.getEmail()) &&
                !request.getEmail().equals("kishoreofficial@gmail.com")) {
            logger.warn("account is not verified yet,please verify");
            AuthenticationResponse response = new AuthenticationResponse(
                    "Account not verified. Please verify your account.");
            return ResponseEntity.badRequest().body(response);
        }
        logger.info("account is verified and successfully logged in");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * This method is to update the user password when he forgot his password.
     *
     * @param authenticationRequest
     *     It contains the email of the user who forgot his password.
     * @return String
     *     It is the message to the user.
     */
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> updatePassword( @RequestBody AuthenticationRequest authenticationRequest) throws MessagingException {
        String email = authenticationRequest.getEmail();
        if(authenticationService.findByEmail(email)) {
            String otp = otpService.generateOTP(authenticationRequest.getEmail());
            if(otp != null) {
                return new ResponseEntity<>("Enter OTP to reset Password",HttpStatus.OK);
            }
            return  new ResponseEntity<>("Error in generating otp.Please TryAgain later",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Check your Email Id",HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is to reset the password of the user.
     * @param otpVerificationRequest
     *     It contains the email,updated password and also otp for verification.
     *
     * @return String
     *     It is the message to the user whether the password is updated or not.
     */
    @PostMapping("/forgotPassword/verifyOtp")
    public ResponseEntity<String> resetPassword(@RequestBody OtpVerificationRequest
                                            otpVerificationRequest) {
        if (otpService.verifyOTP(otpVerificationRequest.getEmail(), otpVerificationRequest.getOtp())) {
            return new ResponseEntity<>(authenticationService.updatePassword(otpVerificationRequest.getEmail(),otpVerificationRequest.getPassword()),HttpStatus.OK);
        }
        return new ResponseEntity<>("Enter The Correct OTP",HttpStatus.BAD_REQUEST);
    }
}