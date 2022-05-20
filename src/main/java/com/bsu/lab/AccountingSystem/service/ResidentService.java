package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface ResidentService extends UserDetailsService {
    boolean save(ResidentDTO residentDTO);

    void save(Resident resident);

    List<ResidentDTO> getAll();

    Resident getResidentByName(String username);

    void updateProfile(ResidentDTO residentDTO);

    void moveOutFromFlat(Long residentId);

    Set<ResidentDTO> getAllUnAcceptedResidents();

    void setResidentAccepted(Long residentId);

    void deleteResident(Long residentId);

    Resident getById(Long residentId);

    ResidentDTO residentToDto(Resident resident);

    Resident getResidentByEmail(String email);

}
