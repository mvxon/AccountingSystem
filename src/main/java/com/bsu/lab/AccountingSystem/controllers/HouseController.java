package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import com.bsu.lab.AccountingSystem.dto.RoomDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.validators.FlatValidator;
import com.bsu.lab.AccountingSystem.validators.HouseValidator;
import com.bsu.lab.AccountingSystem.validators.RoomValidator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.bsu.lab.AccountingSystem.domain.HouseStatus.*;


@Controller
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    private final HouseValidator houseValidator;
    private final FlatValidator flatValidator;
    private final RoomValidator roomValidator;

    @GetMapping("/new")
    public String newHouse(@NotNull Model model) {
        model.addAttribute("house", new HouseDTO());
        return "house/house1";
    }

    @PostMapping("/new")
    public String houseCreatingFirstStep(@ModelAttribute(name = "house") @Valid @NotNull HouseDTO houseDTO,
                                         @NotNull BindingResult bindingResult,
                                         Model model
    ) {
        houseValidator.validate(houseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "house/house1";
        }
        houseDTO.setHouseId(houseService.firstStepSave(houseDTO).getId());
        List<FlatDTO> flatsOfOneFloor = new ArrayList<>();
        for (int i = 0; i < houseDTO.getFlatsPerFloor(); i++) {
            flatsOfOneFloor.add(FlatDTO.builder()
                    .flatNumber(i + 1)
                    .build());
        }
        houseDTO.setFlatsOfOneFloor(flatsOfOneFloor);
        model.addAttribute("house", houseDTO);
        return "house/house2";

    }

    @GetMapping("/load_unfinished_house/{id}")
    public String loadUnfinishedHouse(@PathVariable Long id, Model model) {
        HouseDTO houseDTO = new HouseDTO();
        House house = houseService.getHouseById(id);
        if (house.getStatus() == FINISHED) {
            model.addAttribute("error", "House already finished");
            return "error";
        }
        if (house.getStatus() == CONTINUED) {
            return "redirect:/houses/load_final_step_creation/" + id;
        }
        int flatsPerFloor = houseService.getFlatsPerFloor(house);
        List<FlatDTO> flatsOfOneFloor = new ArrayList<>();

        for (int i = 0; i < flatsPerFloor; i++) {
            flatsOfOneFloor.add(FlatDTO.builder()
                    .flatNumber(i + 1)
                    .build());
        }
        houseDTO.setHouseId(house.getId());
        houseDTO.setFlatsOfOneFloor(flatsOfOneFloor);
        model.addAttribute("house", houseDTO);
        return "house/house2";

    }

    @PostMapping("/flats_filling/{id}")
    public String houseCreatingSecondStep(@PathVariable Long id, HouseDTO houseDTO,
                                          BindingResult bindingResult,
                                          Model model
    ) {
        flatValidator.validate(houseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "house/house2";
        }
        houseDTO.setHouseId(id);
        houseService.secondStepSave(houseDTO);
        List<FlatDTO> flats = houseDTO.getFlatsOfOneFloor();
        for (FlatDTO flat : flats) {
            List<RoomDTO> rooms = new ArrayList<>();
            for (int i = 0; i < flat.getRoomsCount(); i++) {
                rooms.add(RoomDTO.builder()
                        .roomNumber(i + 1)
                        .build());
            }
            flat.setRooms(rooms);
        }
        houseDTO.setFlatsOfOneFloor(flats);
        model.addAttribute("house", houseDTO);
        return "house/house3";

    }

    @GetMapping("/load_final_step_creation/{id}")
    public String loadUnfinishedHouseFinalStep(@PathVariable Long id, Model model) {
        HouseDTO houseDTO = new HouseDTO();
        House house = houseService.getHouseById(id);
        if (house.getStatus() == FINISHED || house.getStatus() == CREATED) {
            model.addAttribute("error", "House already finished or just created");
            return "error";
        }
        houseDTO.setHouseId(house.getId());
        Set<Flat> flats = house.getEntrances().iterator().next().getFloors().iterator().next().getFlats();
        List<FlatDTO> flatDTOS = new ArrayList<>();
        for (Flat flat : flats) {
            List<RoomDTO> rooms = new ArrayList<>();
            for (int i = 0; i < flat.getRoomsCount(); i++) {
                rooms.add(RoomDTO.builder()
                        .roomNumber(i + 1)
                        .build());
            }
            flatDTOS.add(FlatDTO.builder()
                    .flatNumber(flat.getFlatNumber())
                    .rooms(rooms)
                    .build());
        }
        houseDTO.setFlatsOfOneFloor(flatDTOS);
        model.addAttribute("house", houseDTO);
        return "house/house3";

    }

    @PostMapping("/final_step/{id}")
    public String houseCreatingFinalStep(@PathVariable Long id, @ModelAttribute(name = "house") HouseDTO houseDTO,
                                         BindingResult bindingResult,
                                         Model model
    ) {
        House house = houseService.getHouseById(id);
        if (house == null) {
            model.addAttribute("error", "House does not exists");
            return "error";
        }
        if (house.getStatus() == FINISHED || house.getStatus() == CREATED) {
            model.addAttribute("error", "House already finished or just created");
            return "error";
        }
        roomValidator.validate(houseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "house/house3";
        }
        houseDTO.setHouseId(id);
        houseService.save(houseDTO);
        return "redirect:/houses/house/" + house.getId();

    }

    @GetMapping("/unfinished")
    public String unfinishedHouses(Model model) {
        Set<House> houses = houseService.getAllUnFinishedHouses();
        if (houses.size() != 0) {
            model.addAttribute("houses", houseService.getAllUnFinishedHouses());
            return "unfinishedHousesList";
        }
        return "redirect:/houses/new";

    }

    @GetMapping("/delete_unfinished_house/{id}")
    public String deleteHouse(@PathVariable Long id, Model model) {
        if (houseService.getHouseById(id) == null) {
            model.addAttribute("error", "House does not exists");
            return "error";
        }
        houseService.deleteHouse(id);
        return "redirect:/houses/unfinished";

    }

    @GetMapping("/house/{id}")
    public String showHouse(@PathVariable Long id, Model model) {
        if (houseService.getHouseById(id) == null) {
            model.addAttribute("error", "House does not exists");
            return "error";
        }
        model.addAttribute("house", houseService.flatToDto(houseService.getHouseById(id)));
        return "houseInfo";
    }


}
