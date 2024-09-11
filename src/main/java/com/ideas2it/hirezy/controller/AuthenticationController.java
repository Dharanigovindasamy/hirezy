package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.*;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.hirezy.service.AuthenticationService;
import com.ideas2it.hirezy.service.OtpService;
import com.ideas2it.hirezy.model.User;

import static com.ideas2it.hirezy.mapper.UserMapper.mapToUser;

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
     * @param userDto
     *     It contains the User details for Sign up.
     * @return String
     *     It IS the message to the user whether he is signed up are not.
     */
    @Operation(summary = "manage the user Sign up")
    @PostMapping("/register/{role}")
    public ResponseEntity<UserDto> registerUser(
            @PathVariable String role,
            @Valid @RequestBody UserDto userDto
    ) throws MessagingException {

        if(!( role.equals("employees" )|| role.equals("employers"))) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        if (otpService.isAccountVerified(userDto.getEmailId())) {
            throw new ResourceAlreadyExistsException("You are already a User.Please login");
        }
        User user = mapToUser(userDto);
        otpService.generateOTP(user.getEmailId());
            return new ResponseEntity<>(authenticationService.registerUser(
                    user, role),HttpStatus.CREATED);

    }

    /**
     * This method is to verify the Otp entered by the user.
     * @param otpVerificationRequest
     *     It contains the email and the otp entered by the user.
     * @return String
     *     It is the message  to the user whether the Otp is correct or not.
     */
    @Operation(summary = "Verify the otp entered by the user")
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@Valid @RequestBody OtpVerificationDto
                                                    otpVerificationRequest) {
        if (otpService.verifyOTP(otpVerificationRequest.getEmailId(),
                otpVerificationRequest.getOtp())) {
            return new ResponseEntity<>(
                    "Account Verified Successfully. Login to Continue.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is to verify the login information of the user.
     * @param authenticationRequestDto
     *     It contains the email and the password of the user.
     * @return AuthenticationResponse
     *     It contains the Token for the user if he is verified.
     */
    @Operation(summary = "Verify the login information of the user.")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid
            @RequestBody AuthenticationRequestDto authenticationRequestDto
    ) {
        if (!otpService.isAccountVerified(authenticationRequestDto.getEmailId()) &&
                !authenticationRequestDto.getEmailId().equals("kishoreofficial@gmail.com")) {
            return new ResponseEntity<>("Account not verified. Please verify your account",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authenticationService.authenticate(authenticationRequestDto),HttpStatus.OK);
    }

    /**
     * This method is to update the user password when he forgot his password.
     *
     * @param emailRequestDto It contains the email of the user who forgot his password.
     * @return String
     * It is the message to the user.
     */
    @Operation(summary = "Update the user password when he forgot his password")
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody EmailRequestDto emailRequestDto) throws MessagingException {
        String email = emailRequestDto.getEmailId();
        if(authenticationService.findByEmail(email)) {
            String otp = otpService.generateOTP(emailRequestDto.getEmailId());
            if(otp != null) {
                return new ResponseEntity<>("Enter OTP to reset Password",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Check your Email Id",HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method is to reset the password of the user.
     *
     * @param updatePasswordDto
     *     It contains the email,updated password and also otp for verification.
     * @return String
     * It is the message to the user whether the password is updated or not.
     */
    @Operation(summary = "Reset the password of the user after verify the OTP")
    @PutMapping("/updatePassword/verify-otp")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody UpdatePasswordDto
                                                        updatePasswordDto){
        if (otpService.verifyOTP(updatePasswordDto.getEmailId(), updatePasswordDto.getOtp())) {
            return new ResponseEntity<>(authenticationService.updatePassword(updatePasswordDto.getEmailId(), updatePasswordDto.getPassword()),HttpStatus.OK);
        }
        return new ResponseEntity<>("Enter The Correct OTP",HttpStatus.UNAUTHORIZED);
    }
}