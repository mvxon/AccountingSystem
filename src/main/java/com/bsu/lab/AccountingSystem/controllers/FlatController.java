package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FlatController {
    private final ResidentService residentService;
    private final FlatService flatService;


    @GetMapping({"/my_flat", "flat/{id}"})
    public String flatInfo(@PathVariable(required = false) Long id,
                           Model model,
                           Principal principal) {
        if (principal == null) {
            return "login";
        }
        Flat flat = null;
        if (id == null) {
            if (residentService.getResidentByName(principal.getName()).getFlat() == null) {
                model.addAttribute("error", "You do not have flat");
                return "error";
            }
            flat = residentService.getResidentByName(principal.getName()).getFlat();
        } else {
            flat = flatService.getFlatById(id);
        }
        model.addAttribute("flat", flatService.flatToDto(flat));
        return "flatInfo";
    }

    @GetMapping("/flat/delete/{residentId}")
    public String deleteResidentFromFlat(@PathVariable Long residentId, Model model) {
        Resident resident = residentService.getById(residentId);
        Long flatId = resident.getFlat().getId();
        residentService.moveOutFromFlat(residentId);
        return "redirect:/flat/" + flatId;
    }


}
