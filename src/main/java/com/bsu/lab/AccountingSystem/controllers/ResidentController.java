package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;
    private final HouseService houseService;


    public ResidentController(ResidentService residentService,
                              HouseService houseService
    ) {
        this.residentService = residentService;
        this.houseService = houseService;
    }


    @GetMapping("/new")
    public String newResident(Model model) {
        model.addAttribute("resident", new ResidentDTO());
        return "resident";
    }

    @PostMapping("/new")
    public String saveUser(ResidentDTO residentDTO, Model model) {
        if (residentService.save(residentDTO)) {
            return "redirect:/login";
        } else {
            model.addAttribute("resident", residentDTO);
            return "resident";
        }
    }


    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You are not authorized");
        }
        Resident resident = residentService.getResidentByName(principal.getName());

        ResidentDTO userDTO = ResidentDTO.builder()
                .username(resident.getName())
                .email(resident.getEmail())
                .build();
        model.addAttribute("resident", userDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(ResidentDTO residentDTO, Model model, Principal principal) {
        if (principal == null && !Objects.equals(principal.getName(), residentDTO.getUsername())) {
            throw new RuntimeException("You are not authorized");
        }
        if (residentDTO.getPassword() != null
                && !residentDTO.getPassword().isEmpty()
                && Objects.equals(residentDTO.getPassword(), residentDTO.getMatchingPassword())) {
            model.addAttribute("resident", residentDTO);
            return "profile";
        }
        residentService.updateProfile(residentDTO);
        return "redirect:/residents/profile";
    }

    @GetMapping("/profile/{username}")
    public String checkForeignProfile(@PathVariable String username, Model model) {
        Resident resident = residentService.getResidentByName(username);
        ResidentDTO userDTO = ResidentDTO.builder()
                .username(resident.getName())
                .email(resident.getEmail())
                .houseNumber(houseService.getHouseByFlat(resident.getFlat()).getHouseNumber())
                .flatNumber(resident.getFlat().getFlatNumber())
                .role(resident.getRole())
                .flatId(resident.getFlat().getId())
                .build();
        model.addAttribute("resident", userDTO);
        return "foreignProfile";
    }

}

