package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.DTO.ApplicationEditRequestDTO;
import com.vassilyev.submitapp.DTO.ApplicationRequestDTO;
import com.vassilyev.submitapp.controller.util.ApplicationSort;
import com.vassilyev.submitapp.controller.util.RequestRights;
import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.service.ApplicationService;
import com.vassilyev.submitapp.service.StatusService;
import com.vassilyev.submitapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.vassilyev.submitapp.controller.util.StatusType.SENT;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    ApplicationService applicationService;
    UserService userService;
    StatusService statusService;


    @PostMapping("/app/create")
    public Application createApplication(@RequestBody ApplicationRequestDTO applicationRequestDTO) {
        return applicationService.saveNewApplication(applicationRequestDTO);
    }

    @PostMapping("/app/edit")
    public Application editApplication(@RequestBody ApplicationEditRequestDTO applicationEditRequestDTO) {
        try {
            return applicationService.editApplication(applicationEditRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/app/list")
    public Page<Application> listUserApplications(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                  @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                                  @RequestParam("sort") ApplicationSort sort) {
        return applicationService.getAllApplications(
                PageRequest.of(offset, limit, sort.getSortValue()),
                RequestRights.USER);
    }

    @PostMapping("/app/sent")
    public Application sentApplication(@RequestParam(value = "app_id") Long applicationId) {
        return applicationService.editStatusApplication(applicationId, SENT.getValue());
    }




}
