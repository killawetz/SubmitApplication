package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.controller.util.ApplicationSort;
import com.vassilyev.submitapp.controller.util.RequestRights;
import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.model.User;
import com.vassilyev.submitapp.service.ApplicationService;
import com.vassilyev.submitapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vassilyev.submitapp.controller.util.RequestRights.OPERATOR;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    UserService userService;
    ApplicationService applicationService;

    @GetMapping("/userlist")
    public List<User> showUserList() {
        return userService.getUserList();
    }

    @GetMapping("/app/list")
    public Page<Application> listUserApplications(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                  @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                                  @RequestParam("sort") ApplicationSort sort,
                                                  @RequestParam(value = "username", required = false) String username) {
        PageRequest pageRequest = PageRequest.of(offset, limit, sort.getSortValue());
        if (username != null && !username.isEmpty()) {
            return applicationService.getAllApplicationsByUsername(username, pageRequest, RequestRights.ADMIN);
        }
        return applicationService.getAllApplications(pageRequest, RequestRights.ADMIN);
    }

    @PostMapping("/giveop")
    public User giveOpToUser(@RequestParam(value = "username") String username) {
        return userService.setUserRole(username, OPERATOR.getValue());
    }

}
