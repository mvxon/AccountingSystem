package com.bsu.lab.util.consolecontrol.action;


import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AddHouseAction {
    public static void execute(@NotNull Set<House> setOfHouses){
        House house = HouseService.createHouse();
        setOfHouses.add(house);
    }
}
