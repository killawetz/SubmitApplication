package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.DTO.ApplicationEditRequestDTO;
import com.vassilyev.submitapp.DTO.ApplicationRequestDTO;
import com.vassilyev.submitapp.DTO.ApplicationResponseDTO;
import com.vassilyev.submitapp.controller.util.RequestRights;
import com.vassilyev.submitapp.controller.util.StatusType;
import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.model.Status;
import com.vassilyev.submitapp.repository.ApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.vassilyev.submitapp.controller.util.StatusType.*;

@Service
@AllArgsConstructor
public class ApplicationService {

    ApplicationRepository applicationRepository;
    StatusService statusService;
    UserService userService;

    ModelMapper modelMapper;
    final List<String> adminAvailableStatuses = Arrays.asList(
            SENT.getValue(),
            ACCEPTED.getValue(),
            REJECTED.getValue()
    );

    public Application saveNewApplication(ApplicationRequestDTO applicationRequestDTO) {
        Application application = modelMapper.map(applicationRequestDTO, Application.class);
        application.setUser(userService.getCurrentUser());
        application.setCreationTime(new Timestamp(System.currentTimeMillis()));
        application.setLastEditTime(new Timestamp(System.currentTimeMillis()));
        application.setStatus(statusService.getStatusByName(DRAFT.getValue()));
        return applicationRepository.save(application);
    }

    public Application editApplication(ApplicationEditRequestDTO applicationEditRequestDTO) throws Exception {
        Application application = getIfPresent(applicationEditRequestDTO.getId());
        if (!application.getStatus().getName().equals(DRAFT.getValue())) {
            throw new Exception("Нельзя редактировать не черновик");
        }
        application.setTitle(applicationEditRequestDTO.getTitle());
        application.setText(applicationEditRequestDTO.getText());
        application.setLastEditTime(new Timestamp(System.currentTimeMillis()));
        return applicationRepository.save(application);
    }

    public Application editStatusApplication(Long id, String statusName) {
        Status status = statusService.getStatusByName(statusName);
        Application application = getIfPresent(id);
        application.setLastEditTime(new Timestamp(System.currentTimeMillis()));
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    public Page<Application> getAllApplications(PageRequest pageRequest, RequestRights requestRights) {
        Page<Application> page = switch (requestRights) {
            case USER -> applicationRepository.findAllByUser(userService.getCurrentUser(), pageRequest);
            case OPERATOR -> applicationRepository.findAllByStatusName(SENT.getValue(), pageRequest);
            default -> applicationRepository.findAllByStatusNameIn(adminAvailableStatuses, pageRequest);
        };
        return page;
    }

    public Page<Application> getAllApplicationsByUsername(String username,
                                                          PageRequest pageRequest,
                                                          RequestRights requestRights) {
        Page<Application> page = switch (requestRights) {
            case OPERATOR -> applicationRepository.findAllByUserUsernameContainingAndStatusName(username,
                    SENT.getValue(),
                    pageRequest);
            case ADMIN -> {
                yield applicationRepository.findAllByUserUsernameContainingAndStatusNameIn(username,
                        adminAvailableStatuses,
                        pageRequest);
            }
            default -> null;
        };
        return page;
    }

    public Application getIfPresent(Long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return applicationOptional.get();
    }

    public Application changeStatus(Long id, StatusType status) {
        Application application = getIfPresent(id);
        application.setStatus(statusService.getStatusByName(status.getValue()));
        return applicationRepository.save(application);
    }

}
