package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    User findByEmail(String email);

    Set<User> findAllByAcceptedIsTrue();

    Set<User> findAllByAcceptedIsFalse();
}
