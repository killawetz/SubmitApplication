package com.vassilyev.submitapp.repository;

import com.vassilyev.submitapp.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


    List<Application> findAll();

}
