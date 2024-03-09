package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.model.Status;
import com.vassilyev.submitapp.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Status getStatusByName(String name) {
        Optional<Status> optionalStatus = statusRepository.findByName(name);
        if (optionalStatus.isEmpty()) {
            throw new EntityNotFoundException();

        }
        return optionalStatus.get();
    }


}
