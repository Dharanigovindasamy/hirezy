package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.dto.AuthenticationRequestDto;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;

import static com.ideas2it.hirezy.mapper.UserMapper.mapToUserDto;

/**
 * This class manage all the authentication process.
 * It is responsible for the user validation and token generation.
 * @author audhithiyah
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    /**
     * This method is to register the Admin while running the application.
     * By this the admin does not need to register.
     */
    public void registerAdmin(){
        if(! userRepository.existsByEmailId("kishoreofficial@gmail.com")){
            userRepository.save(User.builder()
                    .id(1L)
                    .emailId(System.getenv("ADMIN_EMAIL"))
                    .password(passwordEncoder.encode(System.getenv("ADMIN_PASSWORD")))
                    .phoneNumber(System.getenv("ADMIN_PHONE_NUMBER")).
                    role(Role.builder()
                            .id(1)
                            .roleName("ADMIN")
                            .build())
                    .build());
        }
    }

    /**
     * This method is used to register the user details
     * in the repository
     * @param user {@link User}
     *     It contains the user details for signup.
     * @param role
     *     It is the role of that user who will log in.
     * @return String
     *     Ii is the reply message to the user.
     */
    public UserDto registerUser(User user, String role) {
        String email = user.getEmailId();
        for (User users : userRepository.findAll()){
            if(email.equals(users.getEmailId())){
                throw new ResourceAlreadyExistsException("Email Id - " + email
                        + " Already Exist.Enter OTP to verify your account");
            }
        }
        var users = User.builder()
                .emailId(user.getEmailId())
                .password(passwordEncoder.encode(user.getPassword()))
                .phoneNumber(user.getPhoneNumber())
                .role(roleService.retrieveRoleByName(role))
                .build();
        return mapToUserDto(userRepository.save(users));
    }

    /**
     * This method is to verify the user login information.
     * If it is verified then the token for that user will be generated.
     * @param request {@link AuthenticationRequestDto}
     *     It contains the Email and password of that user.
     * @return AuthenticationResponse
     *     It contains the Token generated for that user.
     */
    public String authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailId(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmailId(request.getEmailId())
                .orElseThrow();
        return jwtService.generateToken(user);
    }

    /**
     * This method is to update the password of the user.
     * @param email
     *     It is the email of the user to  update password.
     * @param password
     *     It is the updated password of the user.
     * @return String
     *     It is the message to the user whether the password is updated or not.
     */
    public String updatePassword(String email, String password) {
        User user = userRepository.findByEmailId(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return  "Password Updated SuccessFully";
    }

    /**
     * This method is to check whether the email is present already.
     * @param email
     *     It is the email of the user to be checked.
     * @return Boolean
     *     Return true if the email is present else false.
     */
    public Boolean findByEmail(String email) {
        return userRepository.findByEmailId(email).isPresent();
    }
}
