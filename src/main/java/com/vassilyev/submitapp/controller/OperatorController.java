package com.vassilyev.submitapp.controller;

import com.vassilyev.submitapp.controller.util.ApplicationSort;
import com.vassilyev.submitapp.controller.util.RequestRights;
import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.service.ApplicationService;
import com.vassilyev.submitapp.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.vassilyev.submitapp.controller.util.StatusType.ACCEPTED;
import static com.vassilyev.submitapp.controller.util.StatusType.REJECTED;

@RestController
@RequestMapping("/op")
@AllArgsConstructor
public class OperatorController {

    ApplicationService applicationService;
    StatusService statusService;

    @GetMapping("/app/list")
    public Page<Application> listUserApplications(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                  @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                                  @RequestParam("sort") ApplicationSort sort,
                                                  @RequestParam(value = "username", required = false) String username) {
        PageRequest pageRequest = PageRequest.of(offset, limit, sort.getSortValue());
        if (username != null && !username.isEmpty()) {
            return applicationService.getAllApplicationsByUsername(username, pageRequest, RequestRights.OPERATOR);
        }
        return applicationService.getAllApplications(pageRequest, RequestRights.OPERATOR);
    }

    @GetMapping("/app/view")
    public Application viewApplication(@RequestParam(value = "app_id") Long id) {
        return applicationService.getIfPresent(id);
    }

    @PostMapping("/app/view")
    public Application acceptApplication(@RequestParam(value = "app_id") Long id) {
        return applicationService.changeStatus(id, ACCEPTED);
    }

    @PostMapping("/app/reject")
    public Application rejectApplication(@RequestParam(value = "app_id") Long id) {
        return applicationService.changeStatus(id, REJECTED);
    }


}
