package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.DTO.AuthRequest;
import com.vassilyev.submitapp.DTO.AuthResponse;
import com.vassilyev.submitapp.model.User;
import com.vassilyev.submitapp.repository.UserRepository;
import com.vassilyev.submitapp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/auth/login")
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request) {
        System.out.println("Request в контроллере " + request);
        return authenticationService.auth(request);
    }

    @GetMapping("/users") // only for admin
    public List<User> allUsers() {
        return userRepository.findAll();
    }

}
