package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.User;
import com.bsu.lab.AccountingSystem.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;


public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);


    void save(User user);

    List<UserDTO> getAll();

    User getUserByName(String username);

    void updateProfile(UserDTO userDTO);

    void moveOutFromFlat(Long userId);

    Set<UserDTO> getAllUnAcceptedUsers();

    void setUserAccepted(Long residentId);

    void deleteUser(Long residentId);

    User getById(Long residentId);

    UserDTO userToDto(User user);

    User getUserByEmail(String email);

    void editUser(UserDTO userDTO);

}
