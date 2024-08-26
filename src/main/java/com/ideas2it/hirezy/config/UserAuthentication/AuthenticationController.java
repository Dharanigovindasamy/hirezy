package com.ideas2it.hirezy.config.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/{role}")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable String role,
            @RequestBody RegisteredRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request,role));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable String role,
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}
