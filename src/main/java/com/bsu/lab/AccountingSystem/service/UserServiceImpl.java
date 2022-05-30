package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.domain.*;
import com.bsu.lab.AccountingSystem.dto.UserDTO;
import com.bsu.lab.AccountingSystem.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HouseService houseService;


    @Override
    public boolean save(UserDTO userDTO) {
        Flat flat = null;
        if (userDTO.getWithFlat()) {
            flat = houseService.getFlatByNumber(houseService.
                    getHouseByHouseNumber(userDTO.getHouseNumber()), userDTO.getFlatNumber());
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.RESIDENT)
                .flat(flat)
                .build();
        if (Objects.equals(userDTO.getUsername(), "max")) {
            user.setAccepted(true);
            user.setRole(Role.ADMIN);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAllByAcceptedIsTrueOrderByName().stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public void updateProfile(UserDTO userDTO) {
        User savedUser = userRepository.findByName(userDTO.getUsername());
        if (savedUser == null) {
            throw new RuntimeException("User with name " + userDTO.getUsername() + " not found");
        }
        boolean isChanged = false;
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            isChanged = true;
        }

        if (!Objects.equals(userDTO.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(userDTO.getEmail());
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(savedUser);
        }

    }

    @Override
    public void moveOutFromFlat(Long userId) {
        User user = userRepository.getById(userId);
        if (user.getFlat() == null) {
            throw new RuntimeException("There is no flat");
        }
        user.setFlat(null);
        userRepository.save(user);
    }

    @Override
    public Set<UserDTO> getAllUnAcceptedUsers() {
        return userRepository.findAllByAcceptedIsFalseOrderByName().stream()
                .map(this::userToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void setUserAccepted(Long userId) {
        User user = userRepository.getById(userId);
        user.setAccepted(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.getById(userId);
        userRepository.delete(user);
    }

    @Override
    public User getById(Long residentId) {
        return userRepository.getById(residentId);
    }

    @Override
    public UserDTO userToDto(User user) {
        House house = user.getFlat() == null ? null : houseService.getHouseByFlat(user.getFlat());
        UserDTO userDTO = UserDTO.builder()
                .userId(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .accepted(user.isAccepted())
                .build();
        if (house != null) {
            userDTO.setFlatId(user.getFlat().getId());
            userDTO.setHouseId(house.getId());
            userDTO.setFlatNumber(user.getFlat().getFlatNumber());
            userDTO.setHouseNumber(house.getHouseNumber());
            userDTO.setWithFlat(true);
        } else {
            userDTO.setWithFlat(false);
        }
        return userDTO;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void editUser(UserDTO userDTO) {
        User user = userRepository.getById(userDTO.getUserId());
        if (!userDTO.getWithFlat()) {
            if (user.getFlat() != null) {
                moveOutFromFlat(user.getId());
            }
        } else {
            House house = houseService.getHouseByHouseNumber(userDTO.getHouseNumber());
            Flat flat = houseService.getFlatByNumber(house, userDTO.getFlatNumber());
            if (user.getFlat() != null) {
                if (!Objects.equals(user.getFlat(), flat))
                    user.setFlat(flat);
            } else {
                user.setFlat(flat);
            }
        }
        if (user.getRole() != userDTO.getRole()) {
            user.setRole(userDTO.getRole());
        }
        if (!Objects.equals(user.getName(), userDTO.getUsername())) {
            user.setName(userDTO.getUsername());
        }
        if (!Objects.equals(user.getEmail(), userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name" + username + " not found");
        }
        if (!user.isAccepted()) {
            throw new RuntimeException("Account is not accepted by administration");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
