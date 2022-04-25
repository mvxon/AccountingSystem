package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.domain.Role;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import com.bsu.lab.AccountingSystem.dao.ResidentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;
    private final HouseService houseService;

    public ResidentServiceImpl(ResidentRepository residentRepository,
                               PasswordEncoder passwordEncoder,
                               HouseService houseService
    ) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
        this.houseService = houseService;
    }

    @Override
    public boolean save(ResidentDTO residentDTO) {
        if (!Objects.equals(residentDTO.getPassword(), residentDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }
        boolean isHouseNumberExists = false;
        boolean isFlatNumberExists;
        for (Integer houseNumber : houseService.findUsedHouseNumbers()) {
            if (Objects.equals(houseNumber, residentDTO.getHouseNumber())) {
                isHouseNumberExists = true;
                break;
            }
        }
        if (isHouseNumberExists) {
            isFlatNumberExists = houseService.isFlatNumberExists(houseService.
                    getHouseByHouseNumber(residentDTO.getHouseNumber()), residentDTO.getFlatNumber());
            if (!isFlatNumberExists) {
                throw new RuntimeException("Flat with number " + residentDTO.getHouseNumber() + " not exists in house " +
                        "number " + residentDTO.getHouseNumber());
            }
        } else {
            throw new RuntimeException("House with number " + residentDTO.getHouseNumber() + " not exists");
        }
        Resident resident = Resident.builder()
                .name(residentDTO.getUsername())
                .password(passwordEncoder.encode(residentDTO.getPassword()))
                .email(residentDTO.getEmail())
                .role(Role.RESIDENT)
                .flat(houseService.getFlatByNumber(houseService.
                        getHouseByHouseNumber(residentDTO.getHouseNumber()), residentDTO.getFlatNumber()))
                .build();
        residentRepository.save(resident);
        return true;
    }

    @Override
    public void save(Resident resident) {
        residentRepository.save(resident);
    }

    @Override
    public List<ResidentDTO> getAll() {
        return residentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Resident getResidentByName(String name) {
        return residentRepository.findByName(name);
    }

    @Override
    public void updateProfile(ResidentDTO residentDTO) {
        Resident savedUser = residentRepository.findByName(residentDTO.getUsername());
        if (savedUser == null) {
            throw new RuntimeException("User with name " + residentDTO.getUsername() + " not found");
        }
        boolean isChanged = false;
        if (residentDTO.getPassword() != null && !residentDTO.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(residentDTO.getPassword()));
            isChanged = true;
        }

        if (!Objects.equals(residentDTO.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(residentDTO.getEmail());
            isChanged = true;
        }

        if (isChanged) {
            residentRepository.save(savedUser);
        }

    }

    @Override
    public void moveOutFromFlat(Resident resident) {
        if (resident.getFlat() == null) {
            throw new RuntimeException("There is no flats");
        }
        resident.setFlat(null);
        residentRepository.save(resident);
    }

    @Override
    public Set<ResidentDTO> getAllUnAcceptedResidents() {
        return residentRepository.findAllByAcceptedIsFalse().stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void setResidentAccepted(Long residentId) {
        Resident resident = residentRepository.findById(residentId);
        resident.setAccepted(true);
        residentRepository.save(resident);
    }

    @Override
    public void deleteResident(Long residentId) {
        Resident resident = residentRepository.findById(residentId);
        residentRepository.delete(resident);
    }

    @Override
    public Resident getById(Long residentId) {
        return residentRepository.findById(residentId);
    }

    private ResidentDTO toDto(Resident resident) {
        return ResidentDTO.builder()
                .username(resident.getName())
                .email(resident.getEmail())
                .flatNumber(resident.getFlat().getFlatNumber())
                .residentId(resident.getId())
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident = residentRepository.findByName(username);
        if (resident == null) {
            throw new UsernameNotFoundException("User with name" + username + " not found");
        }
        if (!resident.isAccepted()) {
            throw new RuntimeException("Account is not accepted by administration");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(resident.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                resident.getName(),
                resident.getPassword(),
                roles
        );
    }
}
