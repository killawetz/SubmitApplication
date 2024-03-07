package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.DTO.AuthRequest;
import com.vassilyev.submitapp.DTO.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse auth(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getName(),
                authRequest.getPassword()
        ));

        var user = userService.userDetailsService().loadUserByUsername(authRequest.getName());

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }
}
