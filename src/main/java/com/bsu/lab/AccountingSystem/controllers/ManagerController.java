package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final ResidentService residentService;

    @Autowired
    public ManagerController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/accept/{id}")
    public String acceptResident(@PathVariable Long id) {
        residentService.setResidentAccepted(id);
        return "redirect:/manager/requests";
    }

    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable Long id) {
        String redirect = residentService.getById(id).isAccepted() ?
                "redirect:/manager/residents" : "redirect:/manager/requests";
        residentService.deleteResident(id);
        return redirect;
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {
        Set<ResidentDTO> residents = residentService.getAllUnAcceptedResidents();
        if (residents.isEmpty()) {
            model.addAttribute("error", "There is no requests available");
            return "error";
        }
        model.addAttribute("residents", residentService.getAllUnAcceptedResidents());
        return "requests";
    }

    @GetMapping("/residents")
    public String residentsList(Model model) {
        model.addAttribute("residents", residentService.getAll());
        return "residentsList";
    }
}
