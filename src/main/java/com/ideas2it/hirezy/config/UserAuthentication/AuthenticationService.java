package com.ideas2it.hirezy.config.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.config.JwtService;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;
import com.ideas2it.hirezy.service.RoleService;

/**
 * This class manage all the authentication process.
 * It is responsible for the user validation and token generation.
 * @author paari
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
        if(userRepository.findById(1L).isEmpty()){
            userRepository.save(User.builder()
                    .Id(1L)
                    .emailId(System.getenv("ADMIN_EMAIL"))
                    .password(passwordEncoder.encode(System.getenv("ADMIN_PASSWORD")))
                    .phoneNumber(System.getenv("ADMIN_PHONE_NUMBER")).
                    role(Role.builder()
                            .Id(1)
                            .roleName("ADMIN")
                            .build())
                    .build());

        }
    }

    /**
     * This method is used to register the user details
     * in the repository
     * @param request {@link RegisteredRequest}
     *     It contains the user details for signup.
     * @param role
     *     It is the role of that user who will log in.
     * @return String
     *     Ii is the reply message to the user.
     */
    public String registerUser(RegisteredRequest request,String role) {
        String email = request.getEmail();
        for (User user : userRepository.findAll()){
            if(email.equals(user.getEmailId())){
                throw new ResourceAlreadyExistsException("Email Id - " + email
                        + " Already Exist.Enter OTP to verify your account");
            }
        }
        var user = User.builder()
                .emailId(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(roleService.retrieveRoleByName(role))
                .build();
        userRepository.save(user);
        return "SignUp Success";
    }

    /**
     * This method is to verify the user login information.
     * If it is verified then the token for that user will be generated.
     * @param request {@link AuthenticationRequest}
     *     It contains the Email and password of that user.
     * @return AuthenticationResponse
     *     It contains the Token generated for that user.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmailId(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
