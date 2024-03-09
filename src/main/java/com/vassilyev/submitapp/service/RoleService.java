package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.model.Role;
import com.vassilyev.submitapp.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        Optional<Role> optionalRole = roleRepository.findByName(name);
        if (optionalRole.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return optionalRole.get();
    }

}
