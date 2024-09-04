package com.ideas2it.hirezy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ideas2it.hirezy.repository.UserRepository;

/**
 * This class is used for providing authentication
 * and uses the Bcrypt password encoder
 * @author audhithiyah
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    /**
     * This method creates a bean for the UserDetailsService.
     * It loads user-specific data during authentication.
     * The method retrieves a user by their email ID from the repository.
     *
     * @return an instance of UserDetailsService
     * @throws UsernameNotFoundException if the user is not found
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmailId(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    /**
     * creates a Spring Bean that returns an instance of DaoAuthenticationProvider,
     * which is configured with a UserDetailsService and a PasswordEncoder.
     * This bean can then be used by Spring Security to authenticate users
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * This method creates a configured instance of the authentication manager.
     * The authentication manager is responsible for processing authentication requests.
     * It delegates the actual verification of user credentials to one or more AuthenticationProvider instances.
     *
     * @param config - the configuration object that provides the authentication manager
     * @return an instance of AuthenticationManager
     * @throws Exception if an error occurs while retrieving the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * This method creates a bean for the PasswordEncoder.
     * BCryptPasswordEncoder is used to encode passwords for security.
     *
     * @return an instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
