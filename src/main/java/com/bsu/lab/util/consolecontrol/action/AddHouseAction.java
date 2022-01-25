package com.bsu.lab.util.consolecontrol.action;


import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddHouseAction {
    public static void execute(@NotNull List<House> arrayOfHouses){
        House house = HouseService.createHouse();
        arrayOfHouses.add(house);
    }
}
