package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Resident findByName(String username);

    Resident findById(Long id);

    Resident findResidentByEmail(String email);

    Set<Resident> findAllByAcceptedIsFalse();
}
