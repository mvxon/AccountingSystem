package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.util.consolecontrol.action.subaction.AvailabilityOfHousesCheck;
import com.bsu.lab.util.input.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RemoveHouseAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if(!AvailabilityOfHousesCheck.check(arrayOfHouses)) return;

        int houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
        arrayOfHouses.remove(houseNumber - 1); // house removing
        System.out.println("Дом успешно удалён!");
    }
}
