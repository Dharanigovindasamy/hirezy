package com.ideas2it.hirezy.config.UserAuthentication;

import com.ideas2it.hirezy.config.JwtService;
import com.ideas2it.hirezy.exception.ResourceAlreadyExistsException;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;
import com.ideas2it.hirezy.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class manage all the authentication process.
 * It is responsible for the user validation and token generation.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    public AuthenticationResponse register(RegisteredRequest request,String role) {
        String email = request.getEmail();
        for (User user : userRepository.findAll()){
            if(email.equals(user.getEmailId())){
                throw new ResourceAlreadyExistsException("Email Id - "+ email
                        + " Already Exist.Please Login or use Another EmailId");
            }
        }
        var user = User.builder()
                .userName(request.getName())
                .emailId(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(roleService.retrieveRoleByName(role))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

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
