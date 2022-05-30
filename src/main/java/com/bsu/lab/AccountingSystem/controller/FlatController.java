package com.bsu.lab.AccountingSystem.controller;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FlatController {
    private final UserService userService;
    private final FlatService flatService;


    @GetMapping({"/my_flat", "flat/{id}"})
    public String flatInfo(@PathVariable(required = false) Long id,
                           Model model,
                           Principal principal) {
        Flat flat = null;
        if (id == null) {
            if (userService.getUserByName(principal.getName()).getFlat() == null) {
                model.addAttribute("error", "You do not have flat");
                return "error";
            }
            flat = userService.getUserByName(principal.getName()).getFlat();
        } else {
            flat = flatService.getFlatById(id);
        }
        model.addAttribute("flat", flatService.flatToDto(flat));
        return "flatInfo";
    }




}
