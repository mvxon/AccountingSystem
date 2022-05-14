package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/residents/requests";
    }

    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
        return "redirect:/residents/requests";
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {
        model.addAttribute("residents", residentService.getAllUnAcceptedResidents());
        return "requests";
    }

    @GetMapping("/residents")
    public String residentsList(Model model) {
        model.addAttribute("residents", residentService.getAll());
        return "residentsList";
    }
}
