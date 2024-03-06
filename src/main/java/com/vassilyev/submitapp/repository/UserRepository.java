package com.vassilyev.submitapp.repository;

import com.vassilyev.submitapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
