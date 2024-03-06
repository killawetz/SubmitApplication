package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.model.User;
import com.vassilyev.submitapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
