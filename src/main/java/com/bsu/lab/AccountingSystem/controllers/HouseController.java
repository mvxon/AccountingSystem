package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.entities.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HouseController {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    HouseService houseService;

    @GetMapping("actions/show/house/{houseNumber}")
    public String showHouse(@PathVariable("houseNumber") int houseNumber, Model model) {
        model.addAttribute("house", houseRepository.findByHouseNumber(houseNumber));
        model.addAttribute("houseService", houseService);
        return "actions/show/house";
    }

    @GetMapping("actions/create/input_house_general_info")
    public String getGeneralHouseInfo() {
        return "actions/create/input_house_general_info";
    }

    @GetMapping("actions/create/create_house")
    public String create(@ModelAttribute("house") House house
            /*@RequestParam("entrances_count") int entrancesCount,
            @RequestParam("floors_count") int floorsCount,
            @RequestParam("flats_per_floor") int flatsPerFloor,*/
    ) {
        /*model.addAttribute(entrancesCount);
        model.addAttribute(flatsPerFloor);
        model.addAttribute(floorsCount);*/
        houseRepository.save(house);
        return "actions/create/create_house";
    }


}
