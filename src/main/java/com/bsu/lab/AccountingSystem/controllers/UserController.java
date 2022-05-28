package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.User;
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
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HouseService houseService;
    private final UserValidator userValidator;

    @GetMapping("/registration")
    public String newResident(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("houses", houseService.allExistingHousesToDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                           BindingResult bindingResult,
                           Model model
    ) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("houses", houseService.allExistingHousesToDto());
            return "registration";
        }
        userService.save(userDTO);
        return "redirect:/login";

    }


    @GetMapping("/profile/update")
    public String profileUser(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", userService.userToDto(user));
        return "updateProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfileUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                                    BindingResult bindingResult
    ) {
        userDTO.setWithFlat(false);
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateProfile";
        }
        userService.updateProfile(userDTO);
        return "redirect:/profile";
    }

    @GetMapping({"/profile/{username}", "/profile"})
    public String checkProfile(@PathVariable(required = false) String username,
                               Model model,
                               Principal principal) {
        User user = userService.getUserByName(username == null ? principal.getName() : username);
        if (user == null) {
            model.addAttribute("error", "User with this name does not exists");
            return "error";
        }
        model.addAttribute("user", userService.userToDto(user));
        return "profile";
    }

}

