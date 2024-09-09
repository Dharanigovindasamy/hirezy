package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.AuthenticationRequestDto;
import com.ideas2it.hirezy.model.Role;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    User user;
    AuthenticationRequestDto request;
    String password = "mock_password";
    String token = "jwt_token";
    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1L)
                .emailId(System.getenv("ADMIN_EMAIL"))
                .password(password)
                .phoneNumber(System.getenv("ADMIN_PHONE_NUMBER"))
                .role(Role.builder()
                .id(1)
                .roleName("ADMIN")
                .build())
                .build();
        request = AuthenticationRequestDto.builder()
                .emailId(System.getenv("ADMIN_EMAIL"))
                .password(password)
                .build();
    }

    @Mock
    private UserRepository userRepository;
    @Mock
            JwtService jwtService;
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;



    @Test
    public void testRegisterAdmin() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
            authenticationService.registerAdmin();
            verify(userRepository,times(1)).save(any(User.class));

        }
    @Test
    public void testAuthenticate() {
        when(userRepository.findByEmailId(request.getEmailId())).thenReturn(Optional.ofNullable(user));
        when(jwtService.generateToken(user)).thenReturn(token);
        String result = authenticationService.authenticate(request);
        assertEquals(token,result);

    }
//    @Test
//    public void testUpdatePassword() {
//        when(userRepository.findByEmailId(request.getEmailId())).thenReturn(Optional.ofNullable(user));
//        when(userRepository.save(any(User.class))).thenReturn(user);
//        authenticationService.updatePassword();
//        verify(userRepository,times(1));
//    }
    }


