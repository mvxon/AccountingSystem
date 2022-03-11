package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @Autowired
    HouseRepository houseRepository;

    @GetMapping("menu/main")
    public String listOfHouses(Model model) {
        model.addAttribute("houses", houseRepository.findAll());
        return "menu/main";
    }

    @GetMapping("actions/show/additional_menu/{houseNumber}")
    public String showAdditionalMenu(@PathVariable("houseNumber") int houseNumber) {
        return "actions/show/additional_menu";
    }


}

