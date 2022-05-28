package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.User;
import com.bsu.lab.AccountingSystem.domain.Role;
import com.bsu.lab.AccountingSystem.dto.UserDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.UserService;
import com.bsu.lab.AccountingSystem.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final UserService userService;
    private final HouseService houseService;
    private final UserValidator userValidator;

    @GetMapping("/accept/{id}")
    public String acceptResident(@PathVariable Long id) {
        userService.setUserAccepted(id);
        return "redirect:/manager/requests";
    }

    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable Long id) {
        String redirect = userService.getById(id).isAccepted() ?
                "redirect:/manager/residents" : "redirect:/manager/requests";
        userService.deleteUser(id);
        return redirect;
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {
        Set<UserDTO> residents = userService.getAllUnAcceptedUsers();
        if (residents.isEmpty()) {
            model.addAttribute("error", "There is no requests available");
            return "error";
        }
        model.addAttribute("users", userService.getAllUnAcceptedUsers());
        return "manager/requests";
    }

    @GetMapping("/users")
    public String residentsList(Model model) {
        List<UserDTO> listOfUsers = userService.getAll();
        if (listOfUsers.size() == 1) {
            model.addAttribute("error", "There is no users");
            return "error";
        }
        model.addAttribute("users", listOfUsers);
        return "manager/usersList";
    }

    @GetMapping("/flat/delete/{residentId}")
    public String deleteResidentFromFlat(@PathVariable Long residentId) {
        User user = userService.getById(residentId);
        Long flatId = user.getFlat().getId();
        userService.moveOutFromFlat(residentId);
        return "redirect:/flat/" + flatId;
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.userToDto(userService.getById(id)));
        model.addAttribute("houses", houseService.allExistingHousesToDto());
        model.addAttribute("roles", Role.values());
        return "manager/editUser";
    }

    @PostMapping("/edit_user/{id}")
    public String saveEditedUser(@PathVariable Long id,
                                 @ModelAttribute(name = "user") @Valid UserDTO userDTO,
                                 BindingResult bindingResult,
                                 Model model
    ) {
        userDTO.setUserId(id);
        User underEditedUser = userService.getById(id);
        userDTO.setAccepted(underEditedUser.isAccepted());
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("houses", houseService.allExistingHousesToDto());
            model.addAttribute("roles", Role.values());
            return "manager/editUser";
        }
        userService.editUser(userDTO);
        if (userService.getById(id).isAccepted()) {
            return "redirect:/manager/users";
        } else {
            userService.setUserAccepted(id);
            return "redirect:/manager/requests";
        }
    }
}
