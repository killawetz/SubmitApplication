package com.vassilyev.submitapp.repository;

import com.vassilyev.submitapp.model.Application;
import com.vassilyev.submitapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


    List<Application> findAll();

    Page<Application> findAllByUser(User user, Pageable pageable);


    Page<Application> findAllByUserUsernameContainingAndStatusName(String userName,
                                                                   String statusName,
                                                                   Pageable pageable);

    Page<Application> findAllByStatusName(String statusName, Pageable pageable);

    Page<Application> findAllByUserUsernameContainingAndStatusNameIn(String username,
                                                                     List<String> statuses,
                                                                     Pageable pageable);

    Page<Application> findAllByStatusNameIn(List<String> statuses, Pageable pageable);

}
