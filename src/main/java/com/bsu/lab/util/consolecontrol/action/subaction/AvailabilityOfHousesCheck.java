package com.bsu.lab.util.consolecontrol.action.subaction;

import com.bsu.lab.model.House;

import java.util.List;

public class AvailabilityOfHousesCheck {
    public static boolean check(List<House> arrayOfHouses){
        if (arrayOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return false;
        }
        return true;
    }
}
