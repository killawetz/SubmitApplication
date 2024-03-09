package com.vassilyev.submitapp.service;

import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.model.Role;
import com.vassilyev.submitapp.model.User;
import com.vassilyev.submitapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private RoleService roleService;


    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User setUserRole(String username, String roleName) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();
        List<Role> roles = user.getRoles();
        roles.add(roleService.getRoleByName(roleName));
        user.setRoles(roles);
        return userRepository.save(user);
    }


    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}
