package com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check;


import com.bsu.lab.AccountingSystem.model.House;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AvailabilityOfHousesCheck {

    public static boolean check(@NotNull Set<House> setOfHouses) {
        if (setOfHouses.size() == 0) {
            System.out.println("Домов нет");
            return true;
        } else return false;
    }
}
