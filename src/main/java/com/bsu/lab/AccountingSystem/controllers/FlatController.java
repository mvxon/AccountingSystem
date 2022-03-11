package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.entities.Entrance;
import com.bsu.lab.AccountingSystem.entities.Flat;
import com.bsu.lab.AccountingSystem.entities.Floor;
import com.bsu.lab.AccountingSystem.entities.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.services.EntranceService;
import com.bsu.lab.AccountingSystem.services.FlatService;
import com.bsu.lab.AccountingSystem.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actions")
public class FlatController {
    final
    HouseRepository houseRepository;
    final
    HouseService houseService;
    final
    FlatService flatService;
    final
    EntranceService entranceService;

    @Autowired
    public FlatController(HouseRepository houseRepository,
                          HouseService houseService,
                          FlatService flatService,
                          EntranceService entranceService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
        this.flatService = flatService;
        this.entranceService = entranceService;
    }

    @GetMapping("show/flat/{houseNumber}/{flatNumber}")
    public String showFlat(
            @PathVariable("houseNumber") int houseNumber,
            @PathVariable("flatNumber") int flatNumber,
            Model model
    ) {
        House house = houseRepository.findByHouseNumber(houseNumber);
        Entrance entrance = houseService.getEntranceByFlatNumber(house, flatNumber);
        Floor floor = entranceService.getFloorByFlatNumber(entrance, flatNumber);
        Flat flat = houseService.getFlatByNumber(house, flatNumber);
        model.addAttribute(house);
        model.addAttribute(flat);
        model.addAttribute(entrance);
        model.addAttribute(floor);
        model.addAttribute(flatService);
        model.addAttribute(houseService);
        model.addAttribute(entranceService);
        return "actions/show/flat";
    }

    @GetMapping("show/flats_list/{houseNumber}")
    public String inputFlatNumber(@PathVariable("houseNumber") int houseNumber, Model model) {
        model.addAttribute("houseNumber", houseNumber);
        model.addAttribute(houseService);
        model.addAttribute("house",houseRepository.findByHouseNumber(houseNumber));
        model.addAttribute("flats", houseService.getFlats(houseRepository.findByHouseNumber(houseNumber)));
        return "actions/show/flats_list";
    }
}
