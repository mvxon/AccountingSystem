package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import com.bsu.lab.AccountingSystem.validators.ResidentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;
    private final ResidentValidator residentValidator;

    @GetMapping("/registration")
    public String newResident(Model model) {
        model.addAttribute("resident", new ResidentDTO());
        return "resident";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute(name = "resident") @Valid ResidentDTO residentDTO,
                           BindingResult bindingResult,
                           Model model
    ) {
        residentValidator.validate(residentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "resident";
        }
        if (residentService.save(residentDTO)) {
            return "redirect:/login";
        } else {
            return "resident";
        }
    }


    @GetMapping("/profile/update")
    public String profileUser(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You are not authorized");
        }
        Resident resident = residentService.getResidentByName(principal.getName());
        model.addAttribute("resident", residentService.residentToDto(resident));
        return "updateProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfileUser(ResidentDTO residentDTO, Model model, Principal principal) {
        if (principal == null && !Objects.equals(principal.getName(), residentDTO.getUsername())) {
            throw new RuntimeException("You are not authorized");
        }
        if (residentDTO.getPassword() != null
                && !residentDTO.getPassword().isEmpty()
                && Objects.equals(residentDTO.getPassword(), residentDTO.getMatchingPassword())) {
            model.addAttribute("resident", residentDTO);
            return "updateProfile";
        }
        residentService.updateProfile(residentDTO);
        return "redirect:/residents/profile";
    }

    @GetMapping({"/profile/{username}", "/profile"})
    public String checkProfile(@PathVariable(required = false) String username,
                               Model model,
                               Principal principal) {
        Resident resident = residentService.getResidentByName(username == null ? principal.getName() : username);
        model.addAttribute("resident", residentService.residentToDto(resident));
        return "profile";
    }

}

