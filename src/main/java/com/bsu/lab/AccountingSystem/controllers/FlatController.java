package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
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
    private final HouseService houseService;
    private final EntranceService entranceService;

    public FlatController(ResidentService residentService,
                          FlatService flatService,
                          HouseService houseService,
                          EntranceService entranceService) {
        this.residentService = residentService;
        this.flatService = flatService;
        this.houseService = houseService;
        this.entranceService = entranceService;
    }

    @GetMapping({"/my_flat", "flat/{id}"})
    public String flatInfo(@PathVariable(required = false) Long id,
                           Model model,
                           Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You are not authorized");
        }
        Flat flat = id == null ?
                residentService.getResidentByName(principal.getName()).getFlat() : flatService.getFlatById(id);

        House house = houseService.getHouseByFlat(flat);
        FlatDTO flatDTO = FlatDTO.builder()
                .flatNumber(flat.getFlatNumber())
                .flatSquare(flatService.findFlatSquare(flat))
                .residents(flat.getResidents())
                .residentsCount(flat.getResidents().size())
                .entranceNumber(houseService.getEntranceByFlatNumber(house, flat.getFlatNumber()).getEntranceNumber())
                .houseNumber(house.getHouseNumber())
                .floorNumber(entranceService.getFloorByFlatNumber(houseService
                                .getEntranceByFlatNumber(house, flat.getFlatNumber()),
                        flat.getFlatNumber()).getFloorNumber())
                .roomsCount(flat.getRoomsCount())
                .build();
        model.addAttribute("flat", flatDTO);
        return "flatInfo";
    }


}
