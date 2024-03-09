package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ApplicationService {

    ApplicationRepository applicationRepository;


    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

}
