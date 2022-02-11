package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;


import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddHouseAction {
    private final HouseService houseService;

    @Autowired
    public AddHouseAction(@Lazy HouseService houseService) {
        this.houseService = houseService;
    }

    public void execute(@NotNull Set<House> setOfHouses) {
        House house = houseService.createHouse();
        setOfHouses.add(house);
    }
}
