package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import com.bsu.lab.AccountingSystem.service.EntranceService;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class FlatController {
    private final ResidentService residentService;
    private final FlatService flatService;

    public FlatController(ResidentService residentService,
                          FlatService flatService
    ) {
        this.residentService = residentService;
        this.flatService = flatService;
    }

    @GetMapping({"/my_flat", "flat/{id}"})
    public String flatInfo(@PathVariable(required = false) Long id,
                           Model model,
                           Principal principal) {
        if (principal == null) {
           return "login";
        }
        Flat flat = id == null ?
                residentService.getResidentByName(principal.getName()).getFlat() : flatService.getFlatById(id);
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
