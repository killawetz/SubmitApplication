package com.vassilyev.submitapp.config;

import com.vassilyev.submitapp.DTO.ApplicationRequestDTO;
import com.vassilyev.submitapp.DTO.ApplicationResponseDTO;
import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.service.ApplicationService;
import com.vassilyev.submitapp.service.StatusService;
import com.vassilyev.submitapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    ApplicationService applicationService;
    UserService userService;
    StatusService statusService;
    @PostMapping("/app/create")
    public Application createApplication(@RequestBody ApplicationRequestDTO applicationRequestDTO) {
        ApplicationResponseDTO applicationResponseDTO = ApplicationResponseDTO.builder()
                .user(userService.getCurrentUser())
                .title(applicationRequestDTO.getTitle())
                .text(applicationRequestDTO.getText())
                .creationTime(new Timestamp(System.currentTimeMillis()))
                .lastEditTime(new Timestamp(System.currentTimeMillis()))
                .status(statusService.getStatusByName("draft"))
                .build();
        applicationService.saveApplication(application);
        return application;
    }

    @PostMapping("/app/edit")
    public Application editApplication(@RequestBody ApplicationRequestDTO applicationRequestDTO) {

    }

    @GetMapping("/apps/list")
    public List<Application> listUserApplications() {

    }

    @PostMapping("/apps/sent")
    public Application sentApplication(@RequestBody Long applicationId) {
        // application.saveApplication(application)
    }

}
