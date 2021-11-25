package com.bsu.lab.util.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RemoveHouseAction {
    public static void execute(@NotNull List<House> arrayOfHouses) {
        if (arrayOfHouses.isEmpty()) {
            System.out.println("Домов нет");
            return;
        }
        int houseNumber;
        do {
             houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses); // entering house number
            if (arrayOfHouses.size() < houseNumber || houseNumber <= 0) {
                System.out.println("Дома с таким номером нет/номер введен неверно!");
            }
        } while (arrayOfHouses.size() < houseNumber || houseNumber <= 0);
        arrayOfHouses.remove(houseNumber - 1); // house removing
        System.out.println("Дом успешно удалён!");
    }
}
