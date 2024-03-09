package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.model.User;
import com.vassilyev.submitapp.repository.ApplicationRepository;
import com.vassilyev.submitapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage")
@AllArgsConstructor
public class ManageController {

    private UserRepository userRepository;
    ApplicationRepository applicationRepository;

    @GetMapping("/apps")
    public List<Application> appsList() {
        return applicationRepository.findAll();
    }

    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }


}
