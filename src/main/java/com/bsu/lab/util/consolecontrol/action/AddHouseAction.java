package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.creator.HouseCreator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddHouseAction {
    public static void execute(@NotNull List<House> arrayOfHouses){
        arrayOfHouses.add(HouseCreator.createHouse());
    }
}
