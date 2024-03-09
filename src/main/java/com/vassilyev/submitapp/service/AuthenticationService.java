package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.DTO.AuthRequest;
import com.vassilyev.submitapp.DTO.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse auth(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        ));
        var user = userService.userDetailsService().loadUserByUsername(authRequest.getUsername());
        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }
}
